package com.mypage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// when trying to fetch sm and display it on the browser
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
