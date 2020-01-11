package com.github.resume.serviceimpl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.github.resume.mapping.GitUser;
import com.github.resume.mapping.GitUserRepo;
import com.github.resume.service.GitResumeService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class GitResumeServiceImpl implements GitResumeService {

	Gson gson = new Gson();

	@Override
	public GitUser getGitUser(String userName) throws UnirestException {
		HttpResponse<String> userResponse = Unirest.get("https://api.github.com/users/" + userName).asString();
		Type type = new TypeToken<GitUser>() {
		}.getType();
		GitUser userDetails = gson.fromJson(userResponse.getBody(), type);
		return userDetails;
	}

	@Override
	public List<GitUserRepo> getGitUserRepos(String userName) throws UnirestException {
		HttpResponse<String> userRepoResponse = Unirest.get("https://api.github.com/users/" + userName + "/repos")
				.asString();
		Type repoType = new TypeToken<List<GitUserRepo>>() {
		}.getType();
		List<GitUserRepo> userRepoDetails = gson.fromJson(userRepoResponse.getBody(), repoType);
		return userRepoDetails;
	}

	@Override
	public Map<String, Integer> getGitUserReposLang(String userName, List<GitUserRepo> repos) throws UnirestException {
		String[] str = repos.stream().map(x -> x.getName()).toArray(String[]::new);
		List<JSONObject> lst = new ArrayList<JSONObject>();
		for (String userRepo : str) {
			HttpResponse<JsonNode> userRepoLangResponse = Unirest
					.get("https://api.github.com/repos/" + userName + "/" + userRepo + "/languages").asJson();
			lst.add(userRepoLangResponse.getBody().getObject());
		}

		Map<String, Integer> langs = new HashMap<String, Integer>();
		for (JSONObject val : lst) {
			Iterator<String> keys = val.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				if (langs.containsKey(key)) {
					Integer sum = langs.get(key) + Integer.parseInt(val.get(key).toString());
					langs.put(key, sum);
				} else {
					langs.put(key, Integer.parseInt(val.get(key).toString()));
				}
			}
		}

		return langs;
	}

}
