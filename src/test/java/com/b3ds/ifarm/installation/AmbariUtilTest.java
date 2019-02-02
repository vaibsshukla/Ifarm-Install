package com.b3ds.ifarm.installation;

import org.junit.Test;

import com.b3ds.ifarm.installation.ambari.AmbariUtil;
import com.b3ds.ifarm.installation.ambari.Validation;

import junit.framework.Assert;

public class AmbariUtilTest {
	
	
	@Test
	public void testServiceStatus()
	{		
				
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
