package com.icaballero.videodowloader.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icaballero.videodowloader.service.DownloadService;

import lombok.extern.slf4j.Slf4j;

/**
 * Factory Servive
 * 
 * @author Ismael Caballero Hernandez
 *
 */
@Slf4j
public class FactoryService {

	private final Map<String, DownloadService> serviceMap = new HashMap<String, DownloadService>();

	/**
	 * Constructor
	 * @param service
	 */
	public FactoryService(List<DownloadService> service) {
		
		log.debug("[FactoryService] - START Constructor");

		for (DownloadService s : service) {

			serviceMap.put(s.getServiceName(), s);

		}

		log.debug("[FactoryService] - END Constructor");
	}

	
	
	/**
	 * Get Service
	 * @param name
	 * @return
	 */
	public DownloadService getService(String name) {
		
		
		return serviceMap.get(name);
		
	}
	
	
	
}
