package com.monkeyframework.boot.config;

import org.apache.log4j.Logger;

import com.monkeyframework.boot.core.Container;
import com.monkeyframework.config.core.factory.ConfigurerFactory;

/**
 * SpringContainer. (SPI, Singleton, ThreadSafe)
 * 
 * @author Cruise.Xu
 */
public class ConfigContainer implements Container {
	
	private static Logger logger = Logger.getLogger(ConfigContainer.class);
		
	private static String CONTAINER_NAME = "ConfigContainer";

	private static long startTime = System.currentTimeMillis();
    
	private ConfigurerFactory factory;
	
	public void start() {
		if(null != factory) {
			factory.setLoadRemote(true);
			factory.init();
		}
	}

    public void stop() {
    }
    
    public void restart() {
    }
    
    public boolean isRunning() {
    	return true;
    }
    
	public ConfigContainer(String configContainer) {
		try {
			Class<?> clazz = Class.forName(configContainer);
			factory = (ConfigurerFactory) clazz.newInstance();
		} catch (Exception e) {
			logger.error("The container instance is error : " + e.getMessage());
		}
	}

	@Override
	public String getName() {
    	return CONTAINER_NAME;
	}
    
	@Override
	public String getType() {
		return CONTAINER_NAME;
	}

	@Override
	public long getStartTime() {
		return startTime;
	}

	public void setFactory(ConfigurerFactory factory) {
		this.factory = factory;
	}

}