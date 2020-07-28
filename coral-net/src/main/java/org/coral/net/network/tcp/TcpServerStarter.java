package org.coral.net.network.tcp;

import org.coral.net.common.NetConfig;
import org.coral.net.core.base.IServerHandler;
import org.coral.net.network.bootstrap.AbstractServer;
import org.coral.net.network.bootstrap.IdleDetectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * TCP服务启动器
 * 
 * @author Jeremy
 * @date 2020年7月9日
 */
public class TcpServerStarter extends AbstractServer {

	private static final Logger log = LoggerFactory.getLogger(TcpServerStarter.class);

	private IServerHandler serverHandler;

	/**
	 * NioEventLoop并不是一个纯粹的I/O线程，它除了负责I/O的读写之外 创建了两个NioEventLoopGroup，
	 * 它们实际是两个独立的Reactor线程池。 一个用于接收客户端的TCP连接，
	 * 另一个用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等。
	 */
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	public TcpServerStarter() {
		super();
	}

	public TcpServerStarter(IServerHandler serverHandler, NetConfig config) {
		super(config.getServerIp(), config.getTcpPort());
		this.serverHandler = serverHandler;
	}

	public TcpServerStarter(IServerHandler serverHandler, String ip, int port) {
		super(ip, port);
		this.serverHandler = serverHandler;
	}

	@Override
	public boolean startServer() throws Exception {
		DefaultThreadFactory bossTf = new DefaultThreadFactory("TCP_SERVER_BOSS");
		bossGroup = new NioEventLoopGroup(1, bossTf);
		int threadCount = Runtime.getRuntime().availableProcessors() * 2; // CPU核数 * 2
		threadCount = 1;// 先默认使用1个线程
		DefaultThreadFactory workerTf = new DefaultThreadFactory("TCP_SERVER_WORKER");
		workerGroup = new NioEventLoopGroup(threadCount, workerTf);

		ChannelFuture future = null;
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)

					.option(ChannelOption.SO_REUSEADDR, true).childOption(ChannelOption.SO_RCVBUF, 128 * 1024)
					.childOption(ChannelOption.SO_SNDBUF, 128 * 1024).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.TCP_NODELAY, true).handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							// idle connection detection
							pipeline.addLast("idleState", new IdleStateHandler(30, 0, 0)); // 默认30秒
							pipeline.addLast("idleDetection", new IdleDetectionHandler());
							// inbound
							pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(8 * 1024, 0, 4, 0, 4));
//							pipeline.addLast(new FixedLengthFrameDecoder(frameLength));
							pipeline.addLast("serverHandler", new TcpServerHandler(serverHandler));
							// outbound
							pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("protocolEncoder", new TcpProtocolEncoder());
						}

					});

			future = bootstrap.bind(port).sync();
			// log.info("逻辑服启动成功, 绑定0.0.0.0, port:{}, 指定ip:{}", port, ip);
		} catch (Exception e) {
			log.info("", e);
			throw new RuntimeException("Netty TCP启动出现异常, 服务器关闭, 请检查");
		} finally {
			if (future != null && future.isSuccess()) {
				log.info("Netty [TCP] server listening {} on port {} and ready for connections...", ip, port);
			} else {
				log.error("Netty [TCP] server start up Error!");
			}
		}

		return false;
	}

	@Override
	public boolean stopServer() throws Exception {
		if (isRunning()) {
			if (bossGroup != null) {
				bossGroup.shutdownGracefully();
			}
			if (workerGroup != null) {
				workerGroup.shutdownGracefully();
			}
			serverHandler.serverStatus(false);// 关服
		}
		log.info("网络服务未在运行状态， 停止失败");
		return false;
	}
	

}
