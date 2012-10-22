package com.celerit.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletOutputStream extends javax.servlet.ServletOutputStream {

	//private HttpResponse response;
	private ByteArrayOutputStream outputStream;
	
	public ServletOutputStream(ByteArrayOutputStream outputStream){
		this.outputStream = outputStream;
	}
	@Override
	public void write(int arg0) throws IOException {
		outputStream.write(arg0);

	}
	
	
	
	

}
