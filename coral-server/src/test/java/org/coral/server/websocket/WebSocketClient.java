package org.coral.server.websocket;

import java.net.InetSocketAddress;
import java.net.URI;

import org.coral.net.network.tcp.TcpProtocolEncoder;
import org.coral.net.network.websocket.WebsocketProtocolEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * 客户端 1.为初始化客户端，创建一个Bootstrap实例
 * 2.为进行事件处理分配了一个NioEventLoopGroup实例，其中事件处理包括创建新的连接以及处理入站和出站数据；
 * 3.当连接被建立时，一个EchoClientHandler实例会被安装到（该Channel的一个ChannelPipeline中；
 * 4.在一切都设置完成后，调用Bootstrap.connect()方法连接到远程节点。
 */
public class WebSocketClient {

	private final String host;
	private final int port;

	public WebSocketClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 运行流程：
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new WebSocketClient("127.0.0.1", 6001).start();
	}

	/**
     * Netty用于接收客户端请求的线程池职责如下。
	     * （1）接收客户端TCP连接，初始化Channel参数；
	     * （2）将链路状态变更事件通知给ChannelPipeline
     */
    private void start() throws Exception {
    	URI uri = new URI("ws://127.0.0.1:6001/websocket");
    	WebSocketClientHandshaker handshaker = 
    			WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders());
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                        	//socketChannel.pipeline().addLast("protocolEncoder", new TcpProtocolEncoder());
                        	
//							//聚合器，使用websocket会用到
                        	socketChannel.pipeline().addLast(new HttpClientCodec());
                        	socketChannel.pipeline().addLast(new HttpObjectAggregator(8192));
                        	
                        	socketChannel.pipeline().addLast(new TcpProtocolEncoder());
                        	socketChannel.pipeline().addLast(new WebsocketProtocolEncoder());
                            socketChannel.pipeline().addLast(new WebSocketClientHandler(handshaker));
                            
                        }
                    });
            //绑定端口
            ChannelFuture f = b.connect().sync();

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully().sync();
        }
    }
}