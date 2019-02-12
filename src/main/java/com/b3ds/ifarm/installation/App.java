package com.b3ds.ifarm.installation;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.server.TcpSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class App {
	
	private final static Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(App.class, args);
		
	}
		
}
