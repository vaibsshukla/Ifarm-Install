package com.b3ds.ifarm.installation;

import java.io.IOException;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class App {
	
	private final static Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		SpringApplication app = new SpringApplication(App.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
		
	}
}
