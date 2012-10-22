package com.celerit.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.HttpHeaders.Names;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;

public class Request implements HttpServletRequest {


	private boolean readingChunks;
	private String[] acceptedLanguage;
	private LinkedHashMap<String, String[]> parameters = new LinkedHashMap<String, String[]>();
	private com.celerit.servlet.ServletInputStream inputStream;
	private byte[] content;
	private String contentType;
	private String protocol;
	private String method;
	public Request(HttpRequest httpRequest){
	
		if (httpRequest.containsHeader(Names.ACCEPT_LANGUAGE)) {
			acceptedLanguage = httpRequest.getHeader(Names.ACCEPT_LANGUAGE)
					.split(",");
		}

		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(
				httpRequest.getUri());
		Map<String, List<String>> params = queryStringDecoder.getParameters();

		while (params.entrySet().iterator().hasNext()) {
			Entry<String, List<String>> entry = params.entrySet().iterator()
					.next();
			this.parameters.put(entry.getKey(),
					entry.getValue().toArray(new String[0]));
		}

		if(httpRequest.containsHeader(Names.CONTENT_TYPE)){
			this.contentType = httpRequest.getHeader(Names.CONTENT_TYPE);
		}
		
		ChannelBuffer content = httpRequest.getContent();
		if (content.readable()) {
			this.content = content.array();
			inputStream = new com.celerit.servlet.ServletInputStream(
					new ByteArrayInputStream(this.content));
		}
		this.protocol = httpRequest.getProtocolVersion().getText();
		this.method = httpRequest.getMethod().getName();
	
	}
	
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getContentLength() {
		return content.length;
	}

	public String getContentType() {
		return this.contentType;
	}

	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		return this.inputStream;
	}

	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Locale getLocale() {
		return new Locale(acceptedLanguage[0].split(";")[0]);
	}

	public Enumeration<Locale> getLocales() {
		Vector<Locale> locales = new Vector<Locale>();
		for(String lang :acceptedLanguage){
			locales.add(new Locale(lang.split(";")[0]));
		}
		return locales.elements();
		
	}

	public String getParameter(String arg0) {
		return parameters.get(arg0)[0];
	}

	public Map<String, String[]> getParameterMap() {
		return parameters;
	}

	public Enumeration<String> getParameterNames() {
		Vector<String> parameterNames = new Vector<String>();
		while(parameters.keySet().iterator().hasNext()){
			parameterNames.add(parameters.keySet().iterator().next());
		}
		return parameterNames.elements();
	}

	public String[] getParameterValues(String arg0) {
		return parameters.get(arg0);
	}

	public String getProtocol() {
		return protocol;
	}

	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(inputStream));
	}

	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerName() {
		// TODO change the name
		return "EPOKU 0.0.1-SNAPSHOT";
	}

	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getMethod() {
		return method;
	}

	
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean authenticate(HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void login(String username, String password) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	public Collection<Part> getParts() throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Part getPart(String name) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
