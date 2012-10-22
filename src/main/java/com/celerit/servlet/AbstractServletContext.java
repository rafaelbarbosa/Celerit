package com.celerit.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.activation.*;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import com.celerit.servlet.ServletRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;

import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServletContext implements javax.servlet.ServletContext {
	private Logger logger = LoggerFactory.getLogger(AbstractServletContext.class);
	
	
	private String contextPath			= null;
	private String servletContextName	= null;
	
	private Map<String, String> initParameters	= new ConcurrentHashMap<String,String>(0);
	private Map<String, Object> attributes 		= new ConcurrentHashMap<String, Object>(0);
	private Map<String, Servlet> servlets 		= new ConcurrentHashMap<String, Servlet>(0);
	private Map<String, String> servletMappings = new ConcurrentHashMap<String, String>(0);
	
	public void setContextPath(String contextPath){
		this.contextPath = contextPath;
	}
	public String getContextPath() {
		return contextPath;
	}

	public javax.servlet.ServletContext getContext(String uripath) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMajorVersion() {
		return 3;
	}

	public int getMinorVersion() {
		return 0;
	}

	public int getEffectiveMajorVersion() {
		return 3;
	}

	public int getEffectiveMinorVersion() {
		return 0;
	}

	public String getMimeType(String file) {
		return new MimetypesFileTypeMap().getContentType(file);
	}

	public Set<String> getResourcePaths(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getResource(String path) throws MalformedURLException {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getResourceAsStream(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestDispatcher getNamedDispatcher(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Servlet getServlet(String name) throws ServletException {
		return servlets.get(name);
	}

	public Enumeration<Servlet> getServlets() {
		return java.util.Collections.enumeration(servlets.values());
	}

	public Enumeration<String> getServletNames() {
		return java.util.Collections.enumeration(servlets.keySet());
	}

	public void log(String msg) {
		logger.info(msg);

	}

	public void log(Exception exception, String msg) {
		logger.error(msg, exception);

	}

	public void log(String message, Throwable throwable) {
		logger.error(message, throwable);

	}

	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerInfo() {
		return "Epoku/1.0";
	}

	public String getInitParameter(String name) {
		return initParameters.get(name);
	}

	public Enumeration<String> getInitParameterNames() {
		return java.util.Collections.enumeration(initParameters.values());
	}

	public boolean setInitParameter(String name, String value) {
		if(initParameters.containsKey(name)){
			return false;
		}
		initParameters.put(name, value);
		return true;
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public Enumeration<String> getAttributeNames() {
		return java.util.Collections.enumeration(attributes.keySet());
	}

	public void setAttribute(String name, Object object) {
		attributes.put(name, object);
	}

	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	public void setServletContextName(String name){
		this.servletContextName = name;
	}
	public String getServletContextName() {
		return this.servletContextName;
	}

	public Dynamic addServlet(String servletName, String className) {
		Dynamic dynamic = new Dynamic();
		try {
			Class<?> clazz = this.getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dynamic;
	}

	public Dynamic addServlet(String servletName, Servlet servlet) {
		// TODO Auto-generated method stub
		return null;
	}

	public Dynamic addServlet(String servletName,
			Class<? extends Servlet> servletClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Servlet> T createServlet(Class<T> clazz)
			throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletRegistration getServletRegistration(String servletName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	public javax.servlet.FilterRegistration.Dynamic addFilter(
			String filterName, String className) {
		// TODO Auto-generated method stub
		return null;
	}

	public javax.servlet.FilterRegistration.Dynamic addFilter(
			String filterName, Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public javax.servlet.FilterRegistration.Dynamic addFilter(
			String filterName, Class<? extends Filter> filterClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Filter> T createFilter(Class<T> clazz)
			throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	public FilterRegistration getFilterRegistration(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	public SessionCookieConfig getSessionCookieConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSessionTrackingModes(
			Set<SessionTrackingMode> sessionTrackingModes) {
		// TODO Auto-generated method stub

	}

	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addListener(String className) {
		// TODO Auto-generated method stub

	}

	public <T extends EventListener> void addListener(T t) {
		// TODO Auto-generated method stub

	}

	public void addListener(Class<? extends EventListener> listenerClass) {
		// TODO Auto-generated method stub

	}

	public <T extends EventListener> T createListener(Class<T> clazz)
			throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	public void declareRoles(String... roleNames) {
		// TODO Auto-generated method stub

	}
	
	public Set<String> addMapping(String servlet, String...mapping){
		
	return null;
		
	}
	public void setServletMappings(Map<String, String> servletMappings) {
		this.servletMappings = servletMappings;
	}
	public Map<String, String> getServletMappings() {
		return servletMappings;
	}
	public Servlet getServletByUri(String uri) {
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);

		for(String mapping : servletMappings.keySet()){
			if(mapping.startsWith("*")){
				if(queryStringDecoder.getPath().endsWith(mapping.substring(1))){
					try {
						return this.getServlet(servletMappings.get(mapping));
					} catch (ServletException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}else if (mapping.endsWith("*")){
				if(queryStringDecoder.getPath().substring(servletContextName.length()).endsWith(mapping.substring(1))){
					try {
						return this.getServlet(servletMappings.get(mapping));
					} catch (ServletException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}
		return null;
		
	}

}
