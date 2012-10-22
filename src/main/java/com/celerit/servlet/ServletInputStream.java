package com.celerit.servlet;

import java.io.IOException;
import java.io.InputStream;

public class ServletInputStream extends javax.servlet.ServletInputStream {

	private InputStream stream;
	
	public ServletInputStream(InputStream inputStream){
		stream = inputStream;
	}
	
	@Override
	public int read() throws IOException {
		return stream.read();
	}

}
