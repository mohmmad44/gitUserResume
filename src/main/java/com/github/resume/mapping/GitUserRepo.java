package com.github.resume.mapping;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GitUserRepo {
	
	private String name;
	private boolean private1;
	private String description;
	private String language;
	private String created_at;
	private Integer forks;
	private Integer watchers;
	private String url;
	private Integer stargazers_count;
	private Integer forks_count;
	private String html_url;
	
	
	
	
	public String getHtml_url() {
		return html_url;
	}
	public Integer getForks_count() {
		return forks_count;
	}
	public Integer getStargazers_count() {
		return stargazers_count;
	}
	public String getName() {
		return name;
	}
	public boolean isPrivate1() {
		return private1;
	}
	public String getDescription() {
		return description;
	}
	
	public String getLanguage() {
		return language;
	}
	public String getCreated_at() {
		return created_at;
	}
	public Integer getForks() {
		return forks;
	}
	public Integer getWatchers() {
		return watchers;
	}
	public String getUrl() {
		return url;
	}
	
	
	
	
	
	
		
	
	

}
