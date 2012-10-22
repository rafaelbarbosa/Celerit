package com.celerit;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.vfs2.FileSystemException;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.celerit.configuration.ContainerConfiguration;
import com.celerit.vfs.VFSManager;
/**
 * Celerit Servlet Container entrance point
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) throws FileSystemException, ConfigurationException
    {
    	long startupTime = System.currentTimeMillis();
        logger.info("_________      ______              _____ _____");
        logger.info("__  ____/_____ ___  /_____ ___________(_)__  /_    ");
        logger.info("_  /     _  _ \\__  / _  _ \\__  ___/__  / _  __/");
        logger.info("/ /___   /  __/_  /  /  __/_  /    _  /  / /_");
        logger.info("\\____/   \\___/ /_/   \\___/ /_/     /_/   \\__/");

         logger.info("Starting Celerit Servlet Container 0.0.1");
		
    	
    	VFSManager.getInstance().loadApps();
    	
    	
    	ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(new HttpServerPipelineFactory());

		// Bind and start to accept incoming connections.
		bootstrap.bind(new InetSocketAddress(ContainerConfiguration.getInstance().getPort()));
		logger.info("Listening on port : {}", ContainerConfiguration.getInstance().getPort()+"");
        long startupTimeElapsed = ((System.currentTimeMillis()-startupTime));
		if( startupTimeElapsed >1000){
            logger.info("Celerit started in {} seconds",startupTimeElapsed/1000);
        }
        else{
            logger.info("Celerit started in {} miliseconds",startupTimeElapsed);
        }



    }
}
