package com.celerit.utils;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestServlet implements Servlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045103591456857181L;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		res.getWriter().println("<html><head><title>Test</title><body><h1>This is a test</h1></body></html>");
		res.getWriter().flush();
		res.getWriter().close();
		
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
