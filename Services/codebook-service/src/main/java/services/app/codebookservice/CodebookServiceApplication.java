package services.app.codebookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CodebookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodebookServiceApplication.class, args);
	}

}
