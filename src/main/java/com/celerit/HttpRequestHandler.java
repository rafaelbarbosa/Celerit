package com.celerit;

import static org.jboss.netty.handler.codec.http.HttpHeaders.is100ContinueExpected;
import static org.jboss.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.SET_COOKIE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.Cookie;
import org.jboss.netty.handler.codec.http.CookieDecoder;
import org.jboss.netty.handler.codec.http.CookieEncoder;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpChunk;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.celerit.servlet.Request;
import com.celerit.servlet.Response;

public class HttpRequestHandler extends  SimpleChannelUpstreamHandler {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);
	private HttpRequest request;
	private boolean readingChunks;
	/** Buffer that stores the response content */
	private final StringBuilder buf = new StringBuilder();
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		logger.trace("Receiving http message");
		
		if (!readingChunks) {
			HttpRequest request = this.request = (HttpRequest) e.getMessage();

			if (is100ContinueExpected(request)) {
				send100Continue(e);
			}
			if (request.isChunked()) {
				readingChunks = true;
			} else {
				writeResponse(e);
			}
		} else {
			HttpChunk chunk = (HttpChunk) e.getMessage();
			if (chunk.isLast()) {
				readingChunks = false;
				writeResponse(e);
			}
		}	
		
		
	}
	
	private void writeResponse(MessageEvent e) throws ServletException, IOException {
		// Decide whether to close the connection or not.
		boolean keepAlive = isKeepAlive(request);

		// Build the response object.
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
		//response.setContent(ChannelBuffers.copiedBuffer(buf.toString(),
		//		CharsetUtil.UTF_8));
		response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");

		if (keepAlive) {
			// Add 'Content-Length' header only for a keep-alive connection.
			response.setHeader(CONTENT_LENGTH, response.getContent()
					.readableBytes());
		}

		// Encode the cookie.
		String cookieString = request.getHeader(COOKIE);
		if (cookieString != null) {
			CookieDecoder cookieDecoder = new CookieDecoder();
			Set<Cookie> cookies = cookieDecoder.decode(cookieString);
			if (!cookies.isEmpty()) {
				// Reset the cookies if necessary.
				CookieEncoder cookieEncoder = new CookieEncoder(true);
				for (Cookie cookie : cookies) {
					cookieEncoder.addCookie(cookie);
				}
				response.addHeader(SET_COOKIE, cookieEncoder.encode());
			}
		}

		
		
		// Write the response.
		try{
			Servlet testServlet = Container.getInstance().getServlet(((HttpRequest)e.getMessage()).getUri());
			
			Response servletResponse = new Response(response,e);
			Request servletRequest = new Request((HttpRequest)e.getMessage());
			
			testServlet.service(servletRequest,servletResponse);
			response.setContent(ChannelBuffers.copiedBuffer(servletResponse.toByteArray()));
		}
		catch(Exception ex){
			logger.error("Error generating response",ex);
			response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			response.setHeader(CONTENT_TYPE, "text/html");
			response.setContent(ChannelBuffers.copiedBuffer(ErrorHandler.errorPage(ex)));
			keepAlive=false;
		}
		
		finally{
			ChannelFuture future = e.getChannel().write(response);
			
			// Close the non-keep-alive connection after the write operation is
			// done.
			
			if (!keepAlive) {
				future.addListener(ChannelFutureListener.CLOSE);
			}
		}
	}

	private void send100Continue(MessageEvent e) {
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, CONTINUE);
		e.getChannel().write(response);
	}

	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}

}
