package com.github.resume.service;

import java.util.List;
import java.util.Map;

import com.github.resume.mapping.GitUser;
import com.github.resume.mapping.GitUserRepo;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface GitResumeService {

	public GitUser getGitUser(String userName) throws UnirestException;

	public List<GitUserRepo> getGitUserRepos(String userName) throws UnirestException;
	
	public Map<String, Integer> getGitUserReposLang(String userName, List<GitUserRepo> repos) throws UnirestException;


}
