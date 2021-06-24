package com.icaballero.videodowloader.domain.models.fembed;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO Response Fembed API
 * @author Ismael Caballero Hernandez
 *
 */
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FembedResponse {

	
	private Boolean success;
	private Player player;
	private List<Data> data = new ArrayList<Data>();
	private List<Object> captions;
	private Boolean is_vr;
	
	
	
	
}
