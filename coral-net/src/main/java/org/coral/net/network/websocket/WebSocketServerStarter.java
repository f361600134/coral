package org.coral.net.network.websocket;

import org.coral.net.common.NetConfig;
import org.coral.net.core.base.IServerHandler;
import org.coral.net.network.bootstrap.AbstractServer;
import org.coral.net.network.bootstrap.IdleDetectionHandler;
import org.coral.net.network.tcp.TcpProtocolEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
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
public class WebSocketServerStarter extends AbstractServer {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServerStarter.class);

	private IServerHandler serverHandler;

	 /**
     * NioEventLoop并不是一个纯粹的I/O线程，它除了负责I/O的读写之外
	     * 创建了两个NioEventLoopGroup，
	     * 它们实际是两个独立的Reactor线程池。
	     * 一个用于接收客户端的TCP连接，
	     * 另一个用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等。
     */
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	public WebSocketServerStarter () {
		super();
	}
			
	public WebSocketServerStarter (IServerHandler serverHandler, NetConfig config) {
		super(config.getServerIp(), config.getTcpPort());
		this.serverHandler = serverHandler;
	}
	
	public WebSocketServerStarter (IServerHandler serverHandler, String ip, int port) {
		super(ip, port);
		this.serverHandler = serverHandler;
	}
	
	@Override
	public boolean startServer() throws Exception {
		DefaultThreadFactory bossTf = new DefaultThreadFactory("WEBSOCKET_SERVER_BOSS");
		bossGroup = new NioEventLoopGroup(1, bossTf);
		int threadCount = Runtime.getRuntime().availableProcessors() * 2; // CPU核数 * 2
		threadCount = 1;
		DefaultThreadFactory workerTf = new DefaultThreadFactory("WEBSOCKET_SERVER_WORKER");
		workerGroup = new NioEventLoopGroup(threadCount, workerTf);

		ChannelFuture future = null;
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_REUSEADDR, true)
			.childOption(ChannelOption.SO_RCVBUF, 128 * 1024)
			.childOption(ChannelOption.SO_SNDBUF, 128 * 1024)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childOption(ChannelOption.TCP_NODELAY, true)
			// heap buf 's
	        // better
	        .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))
	        .handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast("encoder", new HttpResponseEncoder());
							pipeline.addLast("decoder", new HttpRequestDecoder());
							//聚合器，使用websocket会用到
							pipeline.addLast(new HttpObjectAggregator(65536));
							    
							// idle connection detection
							pipeline.addLast("idleState", new IdleStateHandler(30, 0, 0)); // 默认30秒
							pipeline.addLast("idleDetection", new IdleDetectionHandler());
							/*
							 * inbound
							 * websocket
							 * */
							//pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(8 * 1024, 0, 4, 0, 4));
							pipeline.addLast("serverHandler", new WebsocketServerHandler(serverHandler));
							// outbound
							//pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("protocolEncoder", new WebsocketProtocolEncoder());
							
						}

					});

			future = bootstrap.bind(port).sync();
			future.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
		} catch (Exception e) {
			log.info("", e);
			throw new RuntimeException("Netty TCP启动出现异常, 服务器关闭, 请检查");
		}finally {
			if (future != null && future.isSuccess()) {
				serverHandler.serverStatus(true);//开服
                log.info("Netty [WEBSOCKET] server listening {} on port {} and ready for connections...", ip, port);
            } else {
                log.error("Netty [WEBSOCKET] server start up Error!");
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
			serverHandler.serverStatus(false);//关服
		}
		log.info("网络服务未在运行状态， 停止失败");
		return false;
	}

}
