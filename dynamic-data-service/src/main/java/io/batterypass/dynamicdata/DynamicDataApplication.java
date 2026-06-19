package io.batterypass.dynamicdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.batterypass.common", "io.batterypass.dynamicdata"})
public class DynamicDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDataApplication.class, args);
    }
}