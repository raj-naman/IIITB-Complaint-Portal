package com.complaintportal.dashboardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class DashboardServiceApplication {

	@Bean
	//@LoadBalanced   // Service Discovery + Load Balancing
	public RestTemplate getRestTemplate() {
		//HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		//clientHttpRequestFactory.setConnectTimeout(3000);
		//return new RestTemplate(clientHttpRequestFactory);
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DashboardServiceApplication.class, args);
	}

}
