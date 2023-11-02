package com.example.springmicroservice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.example.entity")
@EnableJpaRepositories("com.DAO")
@EnableEurekaClient
@EnableSwagger2
public class SpringMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroServiceApplication.class, args);
	}
	@Bean
	public Docket swaggerconf() {
		return new Docket(DocumentationType.SWAGGER_2).
				select()
				.apis(RequestHandlerSelectors.basePackage("com"))
				.build()
				.apiInfo(apiDetails());
	}
	public ApiInfo apiDetails() {
		return new ApiInfo("SunLife API DOC",
				"Sample API for End User Review", 
				"1.3.4",
				"For Internal Use only",
				new springfox.documentation.service.Contact("Amarjeet", "http://www.asreet-tech.com", "connect@asreet-tech.com"),
				"Api License",
				"http://sunlife.com",
				Collections.emptyList());
	}

}
