package com.b3ds.ifarm.installation.nifi;

import java.io.IOException;

public interface IFaceNifi {
	
	public String getCanvas();
	
	public String deployNifiTemplate(String templateXml) throws IOException;
	
}
