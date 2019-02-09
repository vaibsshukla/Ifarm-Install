package com.b3ds.ifarm.installation.nifi;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.github.hermannpencole.nifi.swagger.ApiClient;
import com.github.hermannpencole.nifi.swagger.ApiException;
import com.github.hermannpencole.nifi.swagger.client.FlowApi;
import com.github.hermannpencole.nifi.swagger.client.ProcessGroupsApi;
import com.github.hermannpencole.nifi.swagger.client.model.FlowEntity;
import com.github.hermannpencole.nifi.swagger.client.model.InstantiateTemplateRequestEntity;
import com.github.hermannpencole.nifi.swagger.client.model.ProcessGroupStatusEntity;
import com.github.hermannpencole.nifi.swagger.client.model.TemplateEntity;

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
		 *  Creates a apiclient for running nifi instance
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
	
}
