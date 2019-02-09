package com.b3ds.ifarm.installation.ambari;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.Credentials;
import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.Service;
import com.b3ds.ifarm.installation.models.ServiceInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AmbariUtil {
	
	private static String AMBARI_HOSTNAME = null;
	private static Integer AMBARI_PORT = null;
	private static String AMBARI_CLUSTER = null;
	private static String AMBARI_USER = null;
	private static String AMBARI_PASSWORD = null;
	private final String AMBARI_VERSION_ENDPOINT = "/api/v1/services/AMBARI/components/AMBARI_SERVER?fields=RootServiceComponents/component_version";
	private final String AMBARI_API_ENDPOINT = "/api/v1/clusters/";
	private final Gson gson = new Gson();
	private String ambariVersion = null;

	private final Logger logger = LogManager.getLogger(AmbariUtil.class);
	
	public void getHDPStackVersion()
	{
		final String url = "http://"+AMBARI_HOSTNAME+":"+AMBARI_PORT+AMBARI_VERSION_ENDPOINT;
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders(AMBARI_USER, AMBARI_PASSWORD)), String.class);
		JsonObject json = gson.fromJson(obj.getBody(), JsonObject.class);
		ambariVersion = json.get("RootServiceComponents").getAsJsonObject().get("component_version").getAsString();
	}
	
	public Response getAllRequiredServices()
	{
		DBUtils utils = new DBUtils();
		List<Service> services =  utils.getAllServices();
		Response response = new Response(200, services, "Success");
		return response;
	}
	
	//Just for testing purpose
	public void setCredentialsForTesting() throws SQLException
	{
		DBUtils utils = new DBUtils();
		Credentials credentials = utils.getCredentials("Ambari");
		AMBARI_CLUSTER = credentials.getClusterName();
		AMBARI_HOSTNAME = credentials.getHostname();
		AMBARI_PASSWORD = credentials.getPassword();
		AMBARI_USER = credentials.getUserName();
		AMBARI_PORT = Integer.parseInt(credentials.getPort());
		
	}
	
	public Response checkAllRequiredHDPServices()
	{
		DBUtils utils = new DBUtils();
		Credentials credentials = null;
		try{
			credentials = utils.getCredentials("Ambari");
			AMBARI_CLUSTER = credentials.getClusterName();
			AMBARI_HOSTNAME = credentials.getHostname();
			AMBARI_PASSWORD = credentials.getPassword();
			AMBARI_USER = credentials.getUserName();
			AMBARI_PORT = Integer.parseInt(credentials.getPort());
			List<Service> services =  utils.getAllServices();
			List<Service> newService = new ArrayList<>();
			Gson gson = new Gson();
			for(Service service : services)
			{
				String ser = getSingleHDPServiceStatus(service.getServiceName().toUpperCase(), service);
				Service serviceUpdated = gson.fromJson(ser, Service.class);
				newService.add(serviceUpdated);
			}
			
			Response response = new Response();
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(newService);
			return response;
		}catch(SQLException e)
		{
			Response response = new Response(500, null, "unable to retrieve Ambari credentials");
			return response;
		}
	}
	
	/*
	 * Check connectivity with ambari
	 */
	public String checkAmbariConnectivity()
	{
		final String url = "http://"+AMBARI_HOSTNAME+":"+AMBARI_PORT+"/api/v1/services/AMBARI/components/AMBARI_SERVER";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = null;
		try{
			obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders(AMBARI_USER, AMBARI_PASSWORD)), String.class);
		}
		catch(ResourceAccessException ex)
		{
			if(ex.contains(UnknownHostException.class))
			{
				String message = "Unable to reach the ambari server. This error is due to invalid hostname or port or your ambari server is down. "
						+ "Please check once again for hostname and confirm that you ambari is running.";
				logger.error(ex.getMessage());
				return message;
			}
			logger.error(ex.getMessage());
			return "Could not access amabari server.";
		}
		return "Unable to reach the ambari server. This error is due to invalid hostname or port or your ambari server is down. "
						+ "Please check once again for hostname and confirm that you ambari is running.";
		
	}
	
	public String getSingleHDPServiceStatus(final String servicename, Service service)
	{
		final String url = "http://"+AMBARI_HOSTNAME+":"+AMBARI_PORT+AMBARI_API_ENDPOINT+AMBARI_CLUSTER+"/services/"+servicename;
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = null;
		try{
			obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders(AMBARI_USER, AMBARI_PASSWORD)), String.class);
		}
		catch(ResourceAccessException ex)
		{
			if(ex.contains(UnknownHostException.class))
			{
				String message = "Unable to reach the ambari server. This error is due to invalid hostname or port or your ambari server is down. "
						+ "Please check once again for hostname and confirm that you ambari is running.";
				return message;
			}
			return "Could not access amabari server.";
		}
		
		catch(HttpClientErrorException ex)
		{
			Service serviced = new Service("Ambari", servicename, "Unknown", "Unknown", "Unknown", "Unknown");
			Gson gson = new Gson();
			return gson.toJson(serviced);
		}
		
		String body = obj.getBody();
		JsonObject json = gson.fromJson(body, JsonObject.class).get("ServiceInfo").getAsJsonObject();
		
		String stack = json.get("desired_stack").getAsJsonObject().get("stackName").getAsString();
		String stackVersion = json.get("desired_stack").getAsJsonObject().get("stackVersion").getAsString();
		String versionResponse = getServiceVersion(servicename, stack, stackVersion);
		JsonObject versionObject = gson.fromJson(versionResponse, JsonObject.class);
		service.setInstalledStatus("Installed");
		service.setRunningStatus(json.get("state").getAsString());
		service.setInstalledVersion(versionObject.get("serviceVersion").getAsString());
		return gson.toJson(service);

	}
	
	public String getServiceVersion(final String serviceName, final String stack, final String version)
	{
		final String versionUrl = "http://"+AMBARI_HOSTNAME+":"+AMBARI_PORT+"/api/v1/stacks/"+stack+"/versions/"+version+"/services/"+serviceName;
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = null;

		try{
			obj = template.exchange(versionUrl, HttpMethod.GET, new HttpEntity<>(createHeaders(AMBARI_USER, AMBARI_PASSWORD)), String.class);
		}
		catch(ResourceAccessException ex)
		{
			if(ex.contains(UnknownHostException.class))
			{
				String message = "Unable to reach the ambari server. This error is due to invalid hostname or port or your ambari server is down. "
						+ "Please check once again for hostname and confirm that you ambari is running.";
				return message;
			}
			return "Could not access amabari server.";
		}
		
		catch(HttpClientErrorException ex)
		{
			ex.printStackTrace();
		}
		
		String body = obj.getBody();
//		JsonObject json = gson.fromJson(body, JsonObject.class).get("ServiceInfo").getAsJsonObject();
		JsonObject json = gson.fromJson(body, JsonObject.class);
		JsonObject res = new JsonObject();
		res.addProperty("serviceVersion", json.get("StackServices").getAsJsonObject().get("service_version").getAsString());
		return gson.toJson(res);

	}
	
	
	public Response getHDPServiceStatus(final String servicename)
	{
		final String url = "http://"+AMBARI_HOSTNAME+":"+AMBARI_PORT+AMBARI_API_ENDPOINT+AMBARI_CLUSTER+"/services/"+servicename+"?fields=ServiceInfo";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = null;
		try{
			obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders(AMBARI_USER, AMBARI_PASSWORD)), String.class);
		}
		catch(ResourceAccessException ex)
		{
			Response res = new Response();
			res.setData(null);
			res.setStatus(400);
			if(ex.contains(UnknownHostException.class))
			{
				String message = "Unable to reach the ambari server. This error is due to invalid hostname or port or your ambari server is down. "
						+ "Please check once again for hostname and confirm that you ambari is running.";
				res.setMessage(message);
			}
			return res;
		}
		
		catch(HttpClientErrorException ex)
		{
			Response res = new Response();
			res.setStatus(ex.getRawStatusCode());
			ServiceInfo service = new ServiceInfo(AMBARI_CLUSTER, null, "N/A", servicename, "Unknown");
			res.setData(service);
			res.setMessage(ex.getLocalizedMessage());
			return res;
		}
		String body = obj.getBody();
		JsonObject json = gson.fromJson(body, JsonObject.class).get("ServiceInfo").getAsJsonObject();
		ServiceInfo info = gson.fromJson(json, ServiceInfo.class);
		Response res = new Response();
		res.setStatus(obj.getStatusCodeValue());
		res.setData(info);
		return res;

	}

	
	private static HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		         set("Content-Type", "application/json");
		         set("X-Requested-By", "ambari");
		         set(ACCEPT, "text/plain");
		      }};
		}

	
}
