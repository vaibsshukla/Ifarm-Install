package com.b3ds.ifarm.installation.ambari;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
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
	
	public static void show()
	{
		
	}
	
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
