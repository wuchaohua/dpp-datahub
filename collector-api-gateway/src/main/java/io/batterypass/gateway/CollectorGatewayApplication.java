package io.batterypass.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.batterypass.common", "io.batterypass.gateway"})
public class CollectorGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectorGatewayApplication.class, args);
    }
}
