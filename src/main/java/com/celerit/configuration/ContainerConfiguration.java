package com.celerit.configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainerConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(ContainerConfiguration.class);
	/**
	 * configuration properties
	 */
	
	private int port;
	private String docRootLocation;
	
	
	private static ContainerConfiguration singleton;

	public static ContainerConfiguration getInstance(){
		if(singleton == null){
			singleton  = new ContainerConfiguration();
		}
		return singleton;
	}
	public ContainerConfiguration(){
		try {
			logger.info("Loading celerit configuration");
			XMLConfiguration config = new XMLConfiguration("celerit.xml");

			port = config.getInt("connector[@port]");
			docRootLocation = config.getString("docroot.location");
		} catch (ConfigurationException e) {
			logger.error(e.getMessage(),e);
			
		}
		singleton = this;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return port;
	}
	public String getDocRootLocation() {
		return docRootLocation;
	}
	
	

}
