package com.b3ds.ifarm.installation.ambari;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean checkHostname(String str)
	{
		Pattern host = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$"); 
		Pattern ip =  Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Matcher ipMatch = ip.matcher(str);
		Matcher hostMatch = host.matcher(str);
		if(hostMatch.matches())
		{
			return true;
		}
		return false;
	}
	
	public static boolean validatePort(Integer port)
	{
		Pattern p = Pattern.compile("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");
		Matcher m = p.matcher(Integer.toString(port));
		if(m.matches())
		{
			return true;
		}
		return false;
	}
}
