package com.bdoloottracker.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PriceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceApplication.class, args);
  }

}
