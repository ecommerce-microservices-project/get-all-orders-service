package tz.ega.allorderservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GetAllOrdersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetAllOrdersServiceApplication.class, args);
	}

}
