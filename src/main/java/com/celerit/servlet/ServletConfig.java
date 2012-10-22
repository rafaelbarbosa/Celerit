package com.celerit.servlet;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.apache.commons.collections.iterators.IteratorEnumeration;

public class ServletConfig implements javax.servlet.ServletConfig {

	private String servletName				= null;
	private ServletContext servletContext	= null;
	private Integer loadOnStartup			= null;
	
	private Map<String, String> initAttributes = new ConcurrentHashMap<String, String>(0);
	private Map<String, String> initParameters = new ConcurrentHashMap<String, String>(0);
	
	
	public String getServletName() {
		return servletName;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public String getInitParameter(String name) {
		return initParameters.get(name);
	}

	public Enumeration<String> getInitParameterNames() {
		return java.util.Collections.enumeration(initParameters.keySet());
	}
	public boolean setInitParameter(String name, String value){
		return initParameters.put(name, value) != null ? false: true;
	}
	public Set<String> setInitParameters(Map<String, String> initParameters){
		this.initParameters = initParameters; 
		return this.initParameters.keySet();
	}
	public Map<String,String> getInitParameters(){
		return initParameters;
	}

	public void setLoadOnStartup(Integer loadOnStartup) {
		this.loadOnStartup = loadOnStartup;
	}

	public Integer getLoadOnStartup() {
		return loadOnStartup;
	}

}
