package com.icaballero.videodowloader.service;

import com.icaballero.videodowloader.exception.DownloadVideoException;

/**
 * Services Contract
 * @author Ismael Caballero Hernandez
 *
 */
public interface DownloadService {

	/**
	 * Download videos
	 * @param url 
	 * @param rute
	 */
	public void getVideo(String url,String rute) throws DownloadVideoException;
	
	
	
	/**
	 * Get Service name
	 * @return service name
	 */
	public String getServiceName();

}
