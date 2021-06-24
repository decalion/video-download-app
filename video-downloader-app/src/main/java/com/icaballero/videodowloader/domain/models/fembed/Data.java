package com.icaballero.videodowloader.domain.models.fembed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	
	private String file;
	private String label;
	private String type;

}
