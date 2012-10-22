package com.celerit.servlet;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.HttpHeaders.Names;
import org.jboss.netty.handler.codec.http.HttpResponse;

public class Response implements HttpServletResponse {

	private MessageEvent e				= null;
	private HttpResponse httpResponse 	= null;
	private ByteArrayOutputStream baos 	= new ByteArrayOutputStream();
	private com.celerit.servlet.ServletOutputStream sos = new com.celerit.servlet.ServletOutputStream(baos);
	private PrintWriter printWriter 	= new PrintWriter(sos);
	
	 
	
	public Response(HttpResponse httpResponse, MessageEvent e){
		this.httpResponse 	= httpResponse;
		this.e 				= e;
		this.httpResponse.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
	}
	
	public void flushBuffer() throws IOException {
		ChannelFuture future = e.getChannel().write(httpResponse);
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCharacterEncoding() {
		return httpResponse.getHeader(Names.CONTENT_ENCODING);
	}

	public String getContentType() {
		return httpResponse.getHeader(Names.CONTENT_TYPE);
	}

	public Locale getLocale() {
		return new Locale(httpResponse.getHeader(Names.CONTENT_LANGUAGE));
	}

	public ServletOutputStream getOutputStream() throws IOException {
		return sos;
	}
	public byte[] toByteArray(){
		return baos.toByteArray();
	}

	public PrintWriter getWriter() throws IOException {
		return printWriter;
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setCharacterEncoding(String arg0) {
		httpResponse.setHeader(Names.CONTENT_ENCODING, arg0);
		
	}

	public void setContentLength(int arg0) {
		httpResponse.setHeader(Names.CONTENT_LENGTH, arg0);
		
	}

	public void setContentType(String arg0) {
		httpResponse.setHeader(Names.CONTENT_TYPE, arg0);
		
	}

	public void setLocale(Locale arg0) {
		httpResponse.setHeader(Names.CONTENT_LANGUAGE, arg0.toString());
		
	}

	
	public void addCookie(javax.servlet.http.Cookie cookie) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}

	
	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}

	
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}

	
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}

	
	public void setIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}

	
	public void addIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}

	
	public void setStatus(int sc) {
		// TODO Auto-generated method stub
		
	}

	
	public void setStatus(int sc, String sm) {
		// TODO Auto-generated method stub
		
	}

	
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Collection<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
