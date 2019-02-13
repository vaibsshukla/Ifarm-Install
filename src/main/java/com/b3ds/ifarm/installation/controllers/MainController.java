package com.b3ds.ifarm.installation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.b3ds.ifarm.installation.ambari.AmbariUtil;
import com.b3ds.ifarm.installation.ambari.Validation;
import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.hdfsdeploy.HdfsDeploy;
import com.b3ds.ifarm.installation.models.Credentials;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.ValidationResponse;
import com.b3ds.ifarm.installation.nifi.Nifideploy;
import com.b3ds.ifarm.installation.utils.MySqlOperations;
import com.github.hermannpencole.nifi.swagger.ApiException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class MainController {

	private final Logger logger = LogManager.getLogger(Controller.class);
	private  RedirectView rv;
	private DBUtils dbUtil = new DBUtils();
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
	
	
	@PostMapping("/deployNifi")
	@ResponseBody
	public String deployNifi() throws SQLException, ApiException, IOException
	{		
		IfarmConfig config = dbUtil.getIfarmConfig();
		Nifideploy deploy = new Nifideploy(config.getNifiHost(), config.getNifiPort());
		deploy.deployNifiTemplate("template.xml");
		deploy.createNifiVariables();
		deploy.updateIfarmRecieving("7f9d331d-e72b-155f-0e41-823c4fe2b490", config.getIfarmDataHost(), config.getIfarmDataPort());
		return "Done";
	}

	@RequestMapping(value="/mysql", method=RequestMethod.GET)
	public String mySQL_Parameters(HttpServletRequest req)
	{
		return "MySqlPage";
	}

	
	@RequestMapping(value="/schemaloaded" ,method=RequestMethod.POST)
	public String getMSqlParameters(HttpServletRequest req) throws SQLException
	{
		String host=req.getParameter("host");
		String port=req.getParameter("port");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		MySqlOperations mysql=new MySqlOperations();
		String url="jdbc:mysql://"+host+":"+port;
		mysql.createDb(url, username, password);
		return "llp";
	}

	
	@PostMapping(value="/toHDFS" )
	@ResponseBody
	public Map<String, String> postDataToHDFS(MultipartHttpServletRequest req,MultipartFile file) throws IOException
	{			
		String host="192.168.1.16";
		String username="b3ds";
		String namenoderpcaddress="0.0.0.0:8020";
		HdfsDeploy hdfs = new HdfsDeploy();
		hdfs.deploy_On_Hdfs(req, file, host, username, namenoderpcaddress);
		
			 Map<String, String> map = new HashMap();
			map.put("Status", "Success");
			return map;

	}
	
	
		
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> fileUpload2(MultipartHttpServletRequest req, HttpServletResponse res) throws Exception
	{
		Gson gson = new Gson();
		String host=req.getParameter("host");
		System.out.println(host);

		Iterator<String> itr = req.getFileNames();
		MultipartFile mpf = null;
		while(itr.hasNext())
		{
			mpf = req.getFile(itr.next());
			InputStream io = mpf.getInputStream();
			System.out.println(io.available());
		}
		
		Map<String, String> map = new HashMap();
		map.put("Status", "Success");
		return map;
	}

	/*	@RequestMapping(value="/remotefiletransfered" ,method=RequestMethod.POST)
	public String getFileTransferParameters(HttpServletRequest req) throws SQLException, IOException, JSchException
	{
		String uploadFile=req.getParameter("uploadFile"); 
		String host=req.getParameter("host"); 
		String userName=req.getParameter("username");
		String password=req.getParameter("password");
		String keyFile=req.getParameter("KeyFile");
		String remoteDirectory=req.getParameter("remoteDirectory");
		
		ScpDeploy fileTransfer = new ScpDeploy();
		fileTransfer.transferFileToRemote(uploadFile, host, userName, password, keyFile, remoteDirectory);
		return "RemoteDetailsDone";
	}
	
*/	


	/*@PostMapping("/getRemoteDetails")
	@ResponseBody
	public String  getRemoteDetails(@RequestBody Object obj) throws IOException, JSchException {
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);		
		SshDetails detail = gson.fromJson(jsonString, SshDetails.class);
		ScpDeploy fileTransfer = new ScpDeploy();
	    Response response = null;
		    try {
				fileTransfer.transferFileToRemote(null, detail.getHost(), detail.getUserName(),
						detail.getPassword(), null, detail.getRemoteDirectory());
			} catch (IOException e) {
				response = new Response(500, null, "File not found.");
				return gson.toJson(response);
			} catch (JSchException e) {
				response = new Response(500, null, "Could not connect to host. Either host is down or invalid details.");
				return gson.toJson(response);
			}
		    
		    response = new Response(200, null, "Success");
		return gson.toJson(response);
	}	
*/	
}
