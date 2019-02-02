package com.b3ds.ifarm.installation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.b3ds.ifarm.installation.ambari.AmbariUtil;
import com.b3ds.ifarm.installation.ambari.Validation;
import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.ValidationResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ch.qos.logback.core.db.dialect.DBUtil;

@Controller
public class MainController {

	private final Logger logger = LogManager.getLogger(Controller.class);
	private static RedirectView rv;
	private final DBUtils dbUtil = new DBUtils();
	private static AmbariUtil UTIL = new AmbariUtil();
	
	@RequestMapping("/")
	public String index(Model model)
	{
		try{
			if (rv.isRedirectView())
			{
				return "index2";
			}
		}catch(NullPointerException npe)
		{
			
		}
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

	@PostMapping()
	public String listAllServices()
	{
		return null;
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
	
}

