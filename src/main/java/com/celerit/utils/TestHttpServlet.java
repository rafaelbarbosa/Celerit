package com.celerit.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestHttpServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.doGet(req, resp);
	resp.setContentType("text/html");
	resp.getWriter().println("<html><head><title>Test</title><body><h1>This is a test</h1></body></html>");
	resp.getWriter().flush();
	
	}
}
