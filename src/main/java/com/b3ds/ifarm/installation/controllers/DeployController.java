package com.b3ds.ifarm.installation.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.b3ds.ifarm.installation.models.Response;
import com.google.gson.Gson;

@Controller
public class DeployController {
	
	private DBUtils dbUtil = new DBUtils();
	private final Logger logger = LogManager.getLogger(DeployController.class);

	@GetMapping("/deploy")
	public String deployPage()
	{
		return "Deploy";
	}
	
	@PostMapping("/saveConfigs")
	@ResponseBody
	public String saveConfigs(@RequestBody Object obj)
	{
		Gson gson = new Gson();
		System.out.println(gson.toJson(obj));
		IfarmConfig obj2 = gson.fromJson(gson.toJson(obj), IfarmConfig.class);
		System.out.println(obj2);
		int i = dbUtil.setIfarmConfig(obj2);
		if(i == 1)
		{
			Response response = new Response(200, obj2, "Configuration updated Successfully.");
			return gson.toJson(response);
		}
		else
		{
			Response response = new Response(500, new IfarmConfig(), "Internal error: Check your sqlite db is present.");
			return gson.toJson(response);
		}
	}

}
