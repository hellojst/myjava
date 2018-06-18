package com.stjia.javabase.nettyproject;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.buffer.ByteBuf;

/**
 * 处理服务器端通道
 * @author stjia
 * 2018.06.18
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		// 以静默方式丢弃接收的数据
//		((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				System.out.println((char) in.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ChannelFuture future = ctx.close();
	}
	
}
