package com.celerit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Servlet;

import com.celerit.servlet.ServletContext;
import org.apache.commons.configuration.XMLConfiguration;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;

import com.celerit.servlet.AbstractServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mantains state of the various servlet contexts loaded
 * @author Rafael Barbosa
 *
 */
public class Container {
	private static final Logger logger = LoggerFactory.getLogger(Container.class);
    private static Container instance;

	
	Map<String, AbstractServletContext> servletContexts = new ConcurrentHashMap<String,AbstractServletContext>();
	
	public static Container getInstance(){
		if(instance == null){
			instance = new Container();
		}
		return instance;
	}
	
	public Container(){
		
		instance = this;
	}

    public AbstractServletContext loadServletContext(XMLConfiguration  webXml){


        ServletContext servletContext = ServletContext.loadServletContext(webXml);
        
        this.servletContexts.put(servletContext.getContextPath(), servletContext);

        logger.info("Servlet context {} added", servletContext.getContextPath());

        return servletContext;

    }



	public Servlet getServlet(String uri) throws NullPointerException{
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
		return servletContexts.get(queryStringDecoder.getPath().split("/")[0]).getServletByUri(uri);
	}
	
	
	
	
	
	

}
