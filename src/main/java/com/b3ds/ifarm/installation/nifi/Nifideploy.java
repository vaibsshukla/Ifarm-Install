package com.b3ds.ifarm.installation.nifi;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.b3ds.ifarm.installation.models.NifiVariableBody;
import com.b3ds.ifarm.installation.models.ProcessGroupRevision;
import com.b3ds.ifarm.installation.models.Variable;
import com.b3ds.ifarm.installation.models.VariableHolder;
import com.b3ds.ifarm.installation.models.VariableRegistry;
import com.github.hermannpencole.nifi.swagger.ApiClient;
import com.github.hermannpencole.nifi.swagger.ApiException;
import com.github.hermannpencole.nifi.swagger.client.FlowApi;
import com.github.hermannpencole.nifi.swagger.client.ProcessGroupsApi;
import com.github.hermannpencole.nifi.swagger.client.ProcessorsApi;
import com.github.hermannpencole.nifi.swagger.client.model.FlowEntity;
import com.github.hermannpencole.nifi.swagger.client.model.InstantiateTemplateRequestEntity;
import com.github.hermannpencole.nifi.swagger.client.model.ProcessGroupEntity;
import com.github.hermannpencole.nifi.swagger.client.model.ProcessGroupStatusEntity;
import com.github.hermannpencole.nifi.swagger.client.model.ProcessorEntity;
import com.github.hermannpencole.nifi.swagger.client.model.TemplateEntity;
import com.google.gson.Gson;

/*
 * Deploying the nifi jobs on a hdp cluster
 * 1)	Get the id of root process-group or main canvas
 */

public class Nifideploy implements IFaceNifi{
	
	private ApiClient apiClient = null;
	private String root_id = null;
	
	private IfarmConfig config = null;
	
	private IfarmConfig getNifiHost()
	{
		DBUtils utils = new DBUtils();
		try{
			config = utils.getIfarmConfig();
			return config;
		}catch(SQLException se)
		{
			return new IfarmConfig();
		}
	}
	
	public Nifideploy(String host, String port) {
		/*
		 *  Creates a api client for running nifi instance
		 */
		apiClient = new ApiClient().setBasePath("http://"+host+":"+port+"/nifi-api");
		
		root_id = getCanvas();
		
	}
	
	/*
	 * Get the details of root process group
	 */
	@Override
	public String getCanvas() {
		
		FlowApi apiInstance = new FlowApi(apiClient);		
		try{
			ProcessGroupStatusEntity result = apiInstance.getProcessGroupStatus("root", true, true,null);
			return result.getProcessGroupStatus().getId();
		}catch(ApiException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String deployNifiTemplate(String templateXml) throws IOException {
		
		Resource res = new ClassPathResource(templateXml); 
		File file = res.getFile();

		ProcessGroupsApi apiInstance = new ProcessGroupsApi(apiClient);
		String templateId = null;

		if(root_id == null)
		{
			throw new NullPointerException("Unable to deploy Nifi Job: nifi root id / canvas is not found.");
		}
		else
		{
			try{
				TemplateEntity result = apiInstance.uploadTemplate(root_id, file);
				templateId = result.getTemplate().getId();
				if(templateId!=null)
				{
					InstantiateTemplateRequestEntity body = new InstantiateTemplateRequestEntity();
					body.setTemplateId(templateId);
					body.setOriginX(0.0);
					body.setOriginY(0.0);
					FlowEntity deployResult = deployTemplate(body, apiInstance);
					System.out.println(deployResult);
				}
			}catch(ApiException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	
	private FlowEntity deployTemplate(InstantiateTemplateRequestEntity body, ProcessGroupsApi instance) throws ApiException
	{
		FlowEntity entity = instance.instantiateTemplate(root_id, body);
		return entity;
	}
	
	private Map<String, String> setVariableValues() throws SQLException
	{
		DBUtils utils = new DBUtils();
		IfarmConfig config = utils.getIfarmConfig();
		Map<String, String> map = new HashMap<>();
		
		return map;
	}
	
	/*
	 * Generate body for nifi variable
	 */
	
	private ProcessGroupEntity getProcessGroupDetail() throws ApiException
	{
		ProcessGroupsApi apiInstance = new ProcessGroupsApi(apiClient);
		ProcessGroupEntity result = apiInstance.getProcessGroup(root_id);
		return result;
	}
	
	public String createNifiVariables() throws SQLException, ApiException
	{
		Long version = this.getProcessGroupDetail().getRevision().getVersion();
		DBUtils utils = new DBUtils();
		Map<String, String> map = utils.getNifiVariableList();
		NifiVariableBody body = this.setnifiVariableBody(map, root_id, version);
		Gson gson = new Gson();
		String varBody = gson.toJson(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(varBody, headers);

		RestTemplate template = new RestTemplate();
		String url = apiClient.getBasePath()+"/process-groups/"+root_id+"/variable-registry";
		ResponseEntity<String> resp = template.exchange(url, HttpMethod.PUT, entity, String.class);
		String r = resp.getBody();
		return r;
	}
	
	private NifiVariableBody setnifiVariableBody(Map<String, String> variableValues, String processGroupId, 
			Long version)
	{
		List<VariableHolder> variables = createVariableHolder(variableValues);
		ProcessGroupRevision processGroupRevision = new ProcessGroupRevision(version);
		VariableRegistry variableRegistry = new VariableRegistry(processGroupId, variables);
		NifiVariableBody body = new NifiVariableBody(processGroupRevision, variableRegistry);
		
		return body;
	}
	
	private List<VariableHolder> createVariableHolder(Map<String, String> variableValues)
	{
		List<VariableHolder> variables = new  ArrayList<>();
		
		Set<String> variableNames = variableValues.keySet();

		for(String variableName : variableNames)
		{
			Variable variable = new Variable(variableName, variableValues.get(variableName));
			VariableHolder holder = new VariableHolder(variable, true);
			variables.add(holder);
		}
		return variables;
	}
	
	public void updateIfarmRecieving(String processorId, String hostname, String port) throws ApiException
	{
		ProcessorsApi apiInstance = new ProcessorsApi(apiClient);
		ProcessorEntity result = apiInstance.getProcessor(processorId);
		result.getComponent().getConfig().getProperties().put("Listening Port", port);
		result.getComponent().getConfig().getProperties().put("Hostname", hostname);		
		apiInstance.updateProcessor(processorId, result);
		
	}
}
