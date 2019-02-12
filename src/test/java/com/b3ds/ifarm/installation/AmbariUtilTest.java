package com.b3ds.ifarm.installation;

import java.sql.SQLException;

import org.junit.Test;

import com.b3ds.ifarm.installation.ambari.AmbariUtil;
import com.b3ds.ifarm.installation.ambari.Validation;
import com.b3ds.ifarm.installation.models.Response;
import com.b3ds.ifarm.installation.models.Service;
import com.google.gson.Gson;

import junit.framework.Assert;

public class AmbariUtilTest {
	
	
//	@Test
	public void testServiceStatus() throws SQLException
	{		
		AmbariUtil util = new AmbariUtil();
		util.setCredentialsForTesting();
//		util.checkAllRequiredHDPServices();
//		Response res = util.getHDPServiceStatus("HDFS");
		Service service = new Service("Ambari", "KAFKA", "Unknown", "Unknown", "0.9", "Unknown");
		String str = util.getSingleHDPServiceStatus(service.getServiceName(), service);
//		String str2 = util.getServiceVersion("KAFKA","HDP","2.6");
		System.out.println(str);
//		System.out.println(str2);
	}
	
	@Test
	public void testCheckAllServiceStatus()
	{
		AmbariUtil util = new AmbariUtil();
		Response res = util.checkAllRequiredHDPServices();
		System.out.println(res);
//		Gson gson = new Gson();		
//		System.out.println(gson.toJson(res));
	}
	
//	@Test
	public void validateHost()
	{
		String hostname = "sdsds";
		Validation valid = new Validation();
		Assert.assertEquals(true, valid.checkHostname(hostname));
	}
	
//	@Test
	public void validatePort()
	{
		Integer port = 8080;
		Validation valid = new Validation();
		Assert.assertEquals(true, valid.validatePort(port));
	}

}
