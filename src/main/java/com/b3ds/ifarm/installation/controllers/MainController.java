package com.b3ds.ifarm.installation.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.b3ds.ifarm.installation.ambari.AmbariUtil;
import com.b3ds.ifarm.installation.ambari.Validation;
import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.Credentials;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.ValidationResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class MainController {

	private final Logger logger = LogManager.getLogger(Controller.class);
	private  RedirectView rv;
	private final DBUtils dbUtil = new DBUtils();
	private AmbariUtil UTIL = new AmbariUtil();
	
	@RequestMapping("/")
	public String index(Model model)
	{
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(gson.toJson(UTIL.getAllRequiredServices()), JsonObject.class);
		model.addAttribute("ServiceList", obj);
		return "index"; 
	}
			
	@PostMapping("/getClusterDetails")
	public String getClusterDetails(HttpServletRequest req, Model model, HttpServletResponse resp)
	{
		String hostname = null;
		Integer port = null;
		String username = null;
		String password = null;
		String cluster = null;

		if(req.getParameter("hdphost") !=null && req.getParameter("hdpport") != null
				&& req.getParameter("hdpcluster") != null && req.getParameter("hdpusername") != null
				&& req.getParameter("password") != null)
		{
			if(Validation.checkHostname(req.getParameter("hdphost")))
			{
				hostname = req.getParameter("hdphost");
			}
			else
			{
				model.addAttribute("message", new ValidationResponse("Ambari Hostname", "Invalid Aambari Host name"));
				return "index";
			}
			if(Validation.validatePort(Integer.parseInt(req.getParameter("hdpport"))))
			{
				port = Integer.parseInt(req.getParameter("hdpport"));			
			}
			else
			{
				model.addAttribute("message", new ValidationResponse("Ambari Port", "Invalid Aambari Port"));
				return "index";
			}
			cluster = req.getParameter("hdpcluster");
			username = req.getParameter("hdpusername");
			password = req.getParameter("hdppassword");
			dbUtil.setCredentials("Ambari", hostname, port.toString(), username, password, cluster);
			return "redirect:/temp";
			
		}else
		{
			model.addAttribute("message", new ValidationResponse("Value null", "There is null value in one of the field"));
			return "index";
		}
		
	}
	
	@RequestMapping("/temp")
	public RedirectView temp()
	{
		System.out.println("reached temp");
		rv = new RedirectView();
		rv.setUrl("/");
		return rv;
	}
	
	@RequestMapping("/get/{service}/status")
	@ResponseBody
	public String getServiceStatus(@PathVariable("service") String service)
	{
		AmbariUtil util = new AmbariUtil();
		Response res = util.getHDPServiceStatus(service);
		Gson gson = new Gson();
		return gson.toJson(res);
	}
	
	@PostMapping("/dbMysql")
	@ResponseBody
	public String getMysqlDetails(HttpServletRequest req, Model model, HttpServletResponse resp)
	{
		String hostname = null;
		Integer port = null;
		String username = null;
		String password = null;
		String cluster = null;
		
		Response response = null;
		
		Gson gson = new Gson();
		if(req.getParameter("mysqlhost") !=null && req.getParameter("mysqlhost") != null
				&& req.getParameter("mysqlusername") != null
				&& req.getParameter("mysqlpassword") != null)
		{
			if(Validation.checkHostname(req.getParameter("mysqlhost")))
			{
				hostname = req.getParameter("mysqlhost");
			}
			else
			{
				model.addAttribute("message", new ValidationResponse("Mysql Hostname", "Invalid Mysql Host name"));
				response = new Response();
				response.setData(null);
				response.setStatus(500);
				response.setMessage("Mysql Hostname : Invalid Mysql Host name");
				return "index_mysql";
			}
			if(Validation.validatePort(Integer.parseInt(req.getParameter("mysqlport"))))
			{
				port = Integer.parseInt(req.getParameter("mysqlport"));			
			}
			else
			{
				model.addAttribute("message", new ValidationResponse("Mysql Port", "Invalid Mysql Port"));
				return "index";
			}
			username = req.getParameter("mysqlusername");
			password = req.getParameter("mysqlpassword");
			dbUtil.setCredentials("Ambari", hostname, port.toString(), username, password, cluster);
			return "redirect:/temp";
			
		}else
		{
			model.addAttribute("message", new ValidationResponse("Value null", "There is null value in one of the field"));
			return "index_mysql";
		}
		
	}
	
	@GetMapping("/getConfigs")
	@ResponseBody
	public String getConfigs()
	{
		Gson gson = new Gson();
		IfarmConfig config = null;
		try {
			config = dbUtil.getIfarmConfig();
			return gson.toJson(new Response(200, config, "Success"));
		} catch (SQLException e) {
			return gson.toJson(new Response(500, new IfarmConfig(), "Configuration Not set."));
		}
	}
	
	@PostMapping("/saveConfigs")
	@ResponseBody
	public String saveConfigs(@RequestBody Object obj)
	{
		Gson gson = new Gson();
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
	
	@PostMapping("/deploymysqldb")
	@ResponseBody
	public String deployMysqlDB()
	{
		return null;
	}
	
	@RequestMapping("/checkAllServices")
	@PostMapping
	@ResponseBody
	public String checkAllService()
	{
		AmbariUtil util = new AmbariUtil();
		Response res = util.checkAllRequiredHDPServices();		
		Gson gson = new Gson();
		return gson.toJson(res);
	}

	@PostMapping("/saveAmbariDetail")
	@ResponseBody
	public String saveAmbariDetail(@RequestBody Object obj)
	{
		Gson gson = new Gson();
		Credentials cred = gson.fromJson(gson.toJson(obj), Credentials.class);
		logger.info(cred);
		int i = dbUtil.setCredentials("Ambari", cred.getHostname(), cred.getPort(), cred.getUserName(), cred.getPassword(), cred.getClusterName());
		if(i == 1)
		{
			Response res = new Response(200, null, "Success");
			return gson.toJson(res);
		}
		Response res = new Response(500, null, "Failed");
		return gson.toJson(res);
	}
}
