package com.b3ds.ifarm.installation.hdfsdeploy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class HdfsDeploy {
			
			public void deploy_On_Hdfs(MultipartHttpServletRequest req,MultipartFile file,String hdfshost, String hdfsusername, String hdfsnamenoderpcaddress ) throws IOException
			{
				Iterator<String> itr = req.getFileNames();
				
				
				MultipartFile mpf = null;
				while(itr.hasNext())
				{
					mpf = req.getFile((itr.next()));
					
					InputStream is = mpf.getInputStream();
					String filename=mpf.getOriginalFilename();
					ResponseEntity<String> res = null;
					File tempFile = File.createTempFile("stream2File",".temp");
					 tempFile.deleteOnExit();
					 try(FileOutputStream out = new FileOutputStream(tempFile))
					 {
						 org.apache.commons.io.IOUtils.copy(is, out);
					 }
					 FileInputStream fis = new FileInputStream(tempFile);
					 byte[] bytes = IOUtils.toByteArray(fis);
//					 String url="http://192.168.1.16:50075/webhdfs/v1/solrdata/"+filename+"?op=CREATE&user.name=b3ds&namenoderpcaddress=0.0.0.0:8020&overwrite=true";
					 String url="http://"+hdfshost+":50075/webhdfs/v1/solrdata/"+filename+"?op=CREATE&user.name="+hdfsusername+"&namenoderpcaddress="+hdfsnamenoderpcaddress+"&overwrite=true";
					 RestTemplate restTemplate = new RestTemplate();
					 HttpEntity<Object> entity = new HttpEntity<Object>(bytes);
					 res =restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
				}

			}
	
}
