package com.b3ds.ifarm.installation;

import org.junit.Test;

import com.b3ds.ifarm.installation.dbdeploy.IFaceDbDeploy;
import com.b3ds.ifarm.installation.dbdeploy.MysqlDeploy;

public class MysqlDeployTest {
	
	@Test
	public void test()
	{
		IFaceDbDeploy db = new MysqlDeploy();
		System.out.println(db.deployDatabase());		
	}
}
