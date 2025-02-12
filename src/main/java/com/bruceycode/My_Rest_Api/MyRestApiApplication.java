package com.bruceycode.My_Rest_Api;

import com.bruceycode.My_Rest_Api.config.DataSourceConfig;
import com.bruceycode.My_Rest_Api.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(DataSourceConfig.class)
public class MyRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRestApiApplication.class, args);
	}



}
