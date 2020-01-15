package com.github.resume.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.resume.mapping.GitUser;
import com.github.resume.mapping.GitUserRepo;
import com.github.resume.service.GitResumeService;

@Controller
public class GitResumeController {

	@Autowired
	GitResumeService gitResumeService;

	@GetMapping("/")
	public String landingPage() {
		return "view/landingPage";
	}

	@GetMapping("/generateResume")
	public String genearteResume(@RequestParam("userName") String userName, Model model) {
		try {
			GitUser user = gitResumeService.getGitUser(userName);
			List<GitUserRepo> repos = gitResumeService.getGitUserRepos(userName);
			
			if (repos != null && repos.size() > 0) {
				List<GitUserRepo> sortedList = repos.stream()
													.sorted((p1, p2)-> (p2.getWatchers() + p2.getForks()) - (p1.getWatchers() + p1.getForks())).limit(5)
													.collect(Collectors.toList());
				
				Map<String, Integer>  languages = gitResumeService.getGitUserReposLang(userName, sortedList);
				model.addAttribute("repos", sortedList);
				model.addAttribute("languages", languages);
				
				int sum = 1;
				if(languages!=null && languages.size()>0) {
					sum = languages.values().stream()
											.reduce(0, Integer::sum);
				}
				model.addAttribute("sum", sum);
			} else {
				model.addAttribute("languages", null);
			}

			model.addAttribute("user", user);
		} catch (Exception e) {
			// user defined exceptions if required

		}

		return "view/resume";

	}

}
