package com.celerit;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ErrorHandler {
	public static byte[] errorPage(Exception ex){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter writer  = new PrintWriter(baos);
		
		writer.write("<html>");
		writer.write("<head><title>500 Internal Server Error - Epoku</title></head>");
		writer.write("<body style=\"font-family:sans-serif\">");
		writer.write("<div><h1>500 Internal server Error</h1></div>");
		writer.write("<div><h2>"+ex.getMessage()+"</h2></div>");
		writer.write("<div><pre><code>");
		ex.printStackTrace(writer);
		writer.write("</code></pre></div>");
		writer.write("<div><h4>Celerit servlet container v0.0.1</h4></div>");
		writer.write("</body>");
		writer.write("</html>");
		writer.flush();
		
		return baos.toByteArray();
	}
}
