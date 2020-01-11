package com.github.resume.mapping;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitUser {
	
	private String name;
	private String location;
	private String email;
	private String blog;
	private String public_repos;
	private Integer followers;
	private String created_at;
	private String avatar_url;
	private String login;
	private String html_url;
	
	
	
	
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public String getEmail() {
		return email;
	}
	public String getBlog() {
		return blog;
	}
	public String getPublic_repos() {
		return public_repos;
	}
	public Integer getFollowers() {
		return followers;
	}
	public String getCreated_at() {
		return created_at;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public String getLogin() {
		return login;
	}
	public String getHtml_url() {
		return html_url;
	}
	
	
	

	
	
	
	
	

}
