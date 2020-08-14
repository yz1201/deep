package cn.dbdj1201.netty.hello.initiallizer;

import cn.dbdj1201.netty.hello.handler.MyHttpServerHandler;
import cn.dbdj1201.netty.hello.handler.MyNettyHttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author: dbdj1201
 * @Date: 2020-08-14 13:34
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("myHttpServerHandler", new MyNettyHttpServerHandler());
    }
}
