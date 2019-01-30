package com.b3ds.ifarm.installation;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

	private final static Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
//		app.setDefaultProperties(Collections.singletonMap("server.port", props.getProperty("app.port")));
		app.setDefaultProperties(Collections.singletonMap("server.port", 8081));
		app.run();

	}
}
