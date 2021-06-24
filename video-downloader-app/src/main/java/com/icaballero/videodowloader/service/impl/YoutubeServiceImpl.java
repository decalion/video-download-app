package com.icaballero.videodowloader.service.impl;

import com.icaballero.videodowloader.exception.DownloadVideoException;
import com.icaballero.videodowloader.service.DownloadService;

public class YoutubeServiceImpl  implements DownloadService{

	@Override
	public void getVideo(String url, String rute) throws DownloadVideoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return "youtube";
	}

}
