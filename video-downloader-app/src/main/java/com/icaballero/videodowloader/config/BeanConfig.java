package com.icaballero.videodowloader.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.icaballero.videodowloader.factory.FactoryService;
import com.icaballero.videodowloader.service.DownloadService;


/**
 * Bean Configuration
 * @author Ismael Caballero
 *
 */
@Configuration
public class BeanConfig {
	
	@Autowired
	private List<DownloadService> services;
	
	
	/**
	 * Configure Factory Service
	 * @return
	 */
	@Bean
	public FactoryService getService() {
		
		
		return new FactoryService(services);
	}

}
