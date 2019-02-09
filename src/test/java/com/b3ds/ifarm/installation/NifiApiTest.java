package com.b3ds.ifarm.installation;

import java.io.IOException;

import org.junit.Test;

import com.b3ds.ifarm.installation.nifi.Nifideploy;

public class NifiApiTest {

	//35ba4bc2-e783-42f5-8d83-75eebc296f1d
	@Test
	public void TestNifiApiClient() throws IOException
	{
		Nifideploy nifi = new Nifideploy("192.168.1.16", "7000");
		nifi.deployNifiTemplate("nifitemplate/Demotemp.xml") ;
	}
}
