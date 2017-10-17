package com.invensist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.invensist.enums.ItemType;
import com.invensist.enums.StoreType;

@Controller
public class PagesController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@GetMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@GetMapping("/")
	public String index(Map<String, Object> model) {
		model.put("message", this.message);
		return "pages/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "pages/login";
	}

	@GetMapping("/users")
	public String users() {
		return "pages/users";
	}

	@GetMapping("/associates")
	public String associates() {
		return "pages/associates";
	}

	@GetMapping("/stores")
	public ModelAndView stores() {
		ModelAndView mv = new ModelAndView("pages/stores");
		mv.addObject("storeTypes", StoreType.values());
		return mv;
	}
	
	@GetMapping("/items")
	public ModelAndView items() {
		ModelAndView mv = new ModelAndView("pages/items");
		mv.addObject("itemTypes", ItemType.values());
		return mv;
	}

	@GetMapping("/reset-password")
	public ModelAndView resetPassword() {
		return new ModelAndView("pages/reset-password");
	}
	
}