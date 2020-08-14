package cn.dbdj1201.netty.hello.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class MyNettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 0. 读取客户端发过来的请求，并将响应返回给客户端的方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest) {

            System.out.println("执行channelRead0");

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求.favicon.ico");
                return;
            }

            /*
             * 1. 构建响应的字符串信息
             */
            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);

            /*
             * 2. 构建FullHttpResponse 对象
             */
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            /*
             * 3. 设置 response 的头信息
             */
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            /*
             * 4. 将response对象返回到客户端
             */
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyHttpServerHandler.channelActive");
        super.channelActive(ctx);
    }

    /**
     * ChannelHandlerContext: 实例是 DefaultChannelHandlerContext
     * 包含了一个ChannelHandler，这里是  MyHttpServerHandler 的实例
     * 包含了一个 DefaultChannelPipeline pipeline：就是 MyServerInitializer#initChannel方法里面的 pipeline
     * DefaultChannelPipeline{(httpServerCodec = io.netty.handler.codec.http.HttpServerCodec), (myHttpServerHandler = com.ghq.netty.one.MyHttpServerHandler)}
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ChannelPipeline pipeline = ctx.pipeline();

        System.out.println("MyHttpServerHandler.channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("MyHttpServerHandler.channelRead");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyHttpServerHandler.channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyHttpServerHandler.channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyHttpServerHandler.channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyHttpServerHandler.channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }
}
