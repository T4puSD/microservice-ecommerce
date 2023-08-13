package com.tapusd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("productService",
                        r -> r.path("/ps/**")
                                .filters(f -> f.rewritePath("^/ps", ""))
                                .uri("lb://PRODUCTSERVICE")
                ).route("customerService",
                        r -> r.path("/cs/**")
                                .filters(f -> f.rewritePath("^/cs", ""))
                                .uri("lb://CUSTOMERSERVICE")
                ).route("orderService",
                        r -> r.path("/os/**")
                                .filters(f -> f.rewritePath("^/os", ""))
                                .uri("lb://ORDERSERVICE")
                )
                .build();
    }
}
