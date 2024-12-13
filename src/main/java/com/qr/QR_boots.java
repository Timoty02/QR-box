package com.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.qr"})
public class QR_boots {
    public static void main(String[] args) {
        SpringApplication.run(QR_boots.class, args);
    }
}
