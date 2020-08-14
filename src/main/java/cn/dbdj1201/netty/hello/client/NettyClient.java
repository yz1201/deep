package cn.dbdj1201.netty.hello.client;

import cn.dbdj1201.netty.hello.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * netty 客户端
 *
 * @Author: dbdj1201
 * @Date: 2020-08-14 10:48
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf1 = b.connect("127.0.0.1", 40923)
                .syncUninterruptibly();
        byte[] msg = "hello netty".getBytes();
        System.out.println("Client: " + Arrays.toString(msg));
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer(msg));
        cf1.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

}
