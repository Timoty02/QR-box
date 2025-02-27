package com.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.qr"})
public class QR_box {
    public static void main(String[] args) {
        SpringApplication.run(QR_box.class, args);
    }
}
