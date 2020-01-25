package com.bdoloottracker.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            r -> r.path("/api/user/**")
                .uri("lb://user-service")
                .id("user-service")
        )
        .route(
            r -> r.path("/api/item/**")
                .uri("lb://item-service")
                .id("item-service")
        )
        .route(
            r -> r.path("/api/loot-table/**")
                .uri("lb://item-service")
                .id("item-service")
        )
        .route(
            r -> r.path("/api/spot/**")
                .uri("lb://item-service")
                .id("item-service")
        )
        .build();
  }
}
