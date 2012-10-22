package com.celerit.servlet;

import com.celerit.servlet.ServletRegistration.Dynamic;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.jboss.netty.channel.StaticChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rafael
 * Date: 13-01-2012
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class ServletContext extends AbstractServletContext {
    private final static Logger logger = LoggerFactory.getLogger(ServletContext.class);    
    public static ServletContext loadServletContext(XMLConfiguration webXml){
        ServletContext servletContext = new ServletContext();
        
        List servlets = webXml.configurationsAt("servlet");
        servletContext.setServletContextName(webXml.getString("display-name"));

        Iterator servletIterator = servlets.iterator();
        while(servletIterator.hasNext()){
            HierarchicalConfiguration servletConfig = (HierarchicalConfiguration)servletIterator.next();
            logger.trace(servletConfig.getString("servlet-name"));
            logger.trace(servletConfig.getString("servlet-class"));
        }

        
        return servletContext;
    }

    @Override
    public Dynamic addServlet(String servletName, String className) {
        return super.addServlet(servletName, className);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
