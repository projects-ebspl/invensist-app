package com.invensist;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		model.put("message", this.message);
		return "pages/dashboard";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "pages/login";
	}

	@RequestMapping("/users")
	public String users() {
		return "pages/users";
	}
}