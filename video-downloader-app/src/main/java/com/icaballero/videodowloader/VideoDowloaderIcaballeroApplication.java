package com.icaballero.videodowloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.icaballero.videodowloader.exception.DownloadVideoException;
import com.icaballero.videodowloader.factory.FactoryService;
import com.icaballero.videodowloader.service.DownloadService;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class
 * 
 * @author Ismael Caballero Hernandez
 *
 */
@SpringBootApplication
@Slf4j
public class VideoDowloaderIcaballeroApplication implements CommandLineRunner {


	@Autowired
	private FactoryService factory;

	public static void main(String[] args) {
		SpringApplication.run(VideoDowloaderIcaballeroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("---START DOWNLOADER SERVICE---");
		
		
		log.info(""+args.length);
		log.info(args[0]);
		log.info(args[1]);


		
		DownloadService service;
		//String url = "https://www.fembed.com/v/-ylwwupmpexlxxr";
		//String path = "D:\\Descargas";
		String url = args[0];
		String path = args[1];

		try {
			
			service = factory.getService(getServiceName(url));
			service.getVideo(url, path);

		} catch (DownloadVideoException e) {

			log.error(e.getMessage());
		} finally {
			
			
			log.info("---END DOWNLOADER SERVICE---");
			
			System.exit(0);
		}
		
		

	}
	
	
	
	private String getServiceName(String url) {
		
		log.debug("[VideoDowloaderIcaballeroApplication.getServiceName()]");
		
		
		Integer firstIndex = url.indexOf(".")+1;
		Integer secondIndex = url.lastIndexOf(".");
		
		String name = url.substring(firstIndex, secondIndex);
		
		log.debug(name);
		
		
		return name.toLowerCase();
		
	}
	
	
	
	
	

}
