package back.vybz.busker_info_read_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BuskerInfoReadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuskerInfoReadServiceApplication.class, args);
	}

}
