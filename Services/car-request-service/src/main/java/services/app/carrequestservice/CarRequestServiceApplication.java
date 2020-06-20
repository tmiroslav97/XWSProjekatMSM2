package services.app.carrequestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CarRequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRequestServiceApplication.class, args);
	}

}
