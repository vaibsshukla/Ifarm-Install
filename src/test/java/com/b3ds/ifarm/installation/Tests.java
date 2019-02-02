package com.b3ds.ifarm.installation;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.ServiceInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Tests {

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

	public static void main(String[] args) {
		System.out.println(getServiceNotFound());
		}
	
	private static Response getServiceNotFound()
	{
		String url ="http://ec2-18-188-39-103.us-east-2.compute.amazonaws.com:8080/api/v1/clusters/Sandbox/services/NIFI?fields=ServiceInfo";
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<String> obj = null;
		try{
				obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders("admin", "Intelcore@i3")), String.class);
		}
		catch(ResourceAccessException ex)
		{
			ex.printStackTrace();
			if(ex.contains(UnknownHostException.class))
			{
				
			}
			return null;
		}
		catch(HttpClientErrorException ex)
		{
			Response res = new Response();
			res.setStatus(ex.getRawStatusCode());
			ex.printStackTrace();
			return res;
		}
		Gson gson = new Gson();
		String body = obj.getBody();
		JsonObject json = gson.fromJson(body, JsonObject.class).get("ServiceInfo").getAsJsonObject();
		ServiceInfo info = gson.fromJson(json, ServiceInfo.class);
		Response res = new Response();
		res.setStatus(obj.getStatusCodeValue());
		res.setData(info);
		System.out.println(res);
		return res;
	}

	private static void getService()
	{
		String url ="http://ec2-18-188-39-103.us-east-2.compute.amazonaws.com:8080/api/v1/clusters/Sandbox/services/NIFI?fields=ServiceInfo";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> obj = template.exchange(url, HttpMethod.GET, new HttpEntity<>(createHeaders("admin", "Intelcore@i3")), String.class);
		Gson gson = new Gson();
		String body = obj.getBody();
		JsonObject json = gson.fromJson(body, JsonObject.class).get("ServiceInfo").getAsJsonObject();
		ServiceInfo info = gson.fromJson(json, ServiceInfo.class);
		Response res = new Response();
		res.setStatus(obj.getStatusCodeValue());
		res.setData(info);
		System.out.println(res);

	}
}
