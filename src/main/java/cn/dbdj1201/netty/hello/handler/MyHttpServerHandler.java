package cn.dbdj1201.netty.hello.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dbdj1201
 * @Date: 2020-08-14 13:36
 */
@Slf4j
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端发过来的请求，并将响应返回给客户端方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            /*
           构建响应字符串
             */
            ByteBuf content = Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);
            //构建FullHttpResponse对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            log.info("server handler，响应数据为：{}", content);
            //回写数据
            ctx.writeAndFlush(response);
        }
    }
}
