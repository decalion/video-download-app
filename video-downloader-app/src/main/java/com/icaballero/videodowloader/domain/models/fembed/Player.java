package com.icaballero.videodowloader.domain.models.fembed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * DTO Class for fembed API
 * @author Ismael Caballero Hernandez
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
	
	
	private String poster_file;
	private String logo_file;
	private String logo_position;
	private String logo_link;
	private Integer logo_margin;
	private String aspectratio;
	private String powered_text;
	private String powered_url;
	private String css_background;
	private String css_text;
	private String css_menu;
	private String css_mntext;
	private String css_caption;
	private String css_cttext;
	private Integer css_ctsize;
	private Integer css_ctopacity;
	private String css_ctedge;
	private String css_icon;
	private String css_ichover;
	private String css_tsprogress;
	private String css_tsrail;
	private String css_button;
	private String css_bttext;
	private Boolean opt_autostart;
	private Boolean opt_title;
	private Boolean opt_quality;
	private Boolean opt_download;
	private Boolean opt_sharing;
	private Boolean opt_playrate;
	private Boolean opt_mute;
	private Boolean opt_loop;
	private Boolean opt_vr;
	private Boolean opt_cast;
	private Boolean opt_nodefault;
	private Boolean opt_forceposter;
	private Boolean opt_parameter;
	private String restrict_domain;
	private String restrict_action;
	private String restrict_target;
    private Boolean resume_enable;
    private String resume_text;
    private String resume_yes;
    private String resume_no;
    private Boolean adb_enable;
	private Integer adb_offset;
	private String adb_text;
    private Boolean ads_adult;
	private Boolean ads_pop;
	private Boolean ads_vast;
	private Boolean ads_free;
	private String trackingId;
    private Boolean income;
	private Boolean incomePop;
	private String logger;
	private String revenue;
	private String revenue_fallback;
	private String revenue_track;

}
