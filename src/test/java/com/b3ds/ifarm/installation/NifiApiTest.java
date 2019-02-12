package com.b3ds.ifarm.installation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.b3ds.ifarm.installation.models.NifiVariableBody;
import com.b3ds.ifarm.installation.models.ProcessGroupRevision;
import com.b3ds.ifarm.installation.models.Variable;
import com.b3ds.ifarm.installation.models.VariableHolder;
import com.b3ds.ifarm.installation.models.VariableRegistry;
import com.b3ds.ifarm.installation.nifi.Nifideploy;
import com.github.hermannpencole.nifi.swagger.ApiException;
import com.google.gson.Gson;

public class NifiApiTest {

	//35ba4bc2-e783-42f5-8d83-75eebc296f1d
	//@Test
	public void testNifiApiClient() throws IOException
	{
		Nifideploy nifi = new Nifideploy("192.168.1.16", "7000");
		nifi.deployNifiTemplate("nifitemplate/Demotemp.xml") ;
	}
	
//	@Test
	public void testNifiVariable()
	{
		Gson gson = new Gson();
		List<VariableHolder> variables = new ArrayList<>();
		Variable v1 = new Variable("Hello", "Demo");
		Variable v2 = new Variable("Fire", "Ola");
		
		VariableHolder vh1 = new VariableHolder(v1, true);
		VariableHolder vh2 = new VariableHolder(v2, true);
		variables.add(vh1);
		variables.add(vh2);
		VariableRegistry registry = new VariableRegistry("sfdjdsjfnjdsafnjkafn", variables);
		
		ProcessGroupRevision pgr = new ProcessGroupRevision(2L);
		
		NifiVariableBody body = new NifiVariableBody(pgr, registry);
		
		System.out.println(gson.toJson(body));
	}
	
	@Test
	public void testNifiVariableBody() throws SQLException, ApiException
	{
		Nifideploy dep = new Nifideploy("192.168.1.16", "7000");
		dep.updateIfarmRecieving("dc8e69ac-0168-1000-ca2c-80dcfba4b2c6", "192.168.1.16", "7000");
	//	String str = dep.createNifiVariables();
		
/*		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(str, headers);

		RestTemplate template = new RestTemplate();
		String url = "http://192.168.1.16:7000/nifi-api/process-groups/b3677b64-0162-1000-36c1-a3a2390c7ead/variable-registry";
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.PUT, entity, String.class);
		resp.getBody();*/
	}
}
