package com.b3ds.ifarm.installation.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private final Logger logger = LogManager.getLogger(Controller.class);
		
	@RequestMapping("/")
	public String index(HttpServletRequest req)
	{
		return "index";
	}

}
