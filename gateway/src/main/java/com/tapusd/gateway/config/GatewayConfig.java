package com.tapusd.gateway.config;

import com.tapusd.gateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("productService",
                        r -> r.path("/ps/**")
                                .filters(f -> f.rewritePath("^/ps", "")
                                        .filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                )
                                .uri("lb://PRODUCTSERVICE") // using `lb` prefix to indicate gateway to use the eureka
                ).route("customerService",
                        r -> r.path("/cs/**")
                                .filters(f -> f.rewritePath("^/cs", "")
                                        .filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                )
                                .uri("lb://CUSTOMERSERVICE") // using registered eureka clients name to route traffic with load balancer
                ).route("orderService",
                        r -> r.path("/os/**")
                                .filters(f -> f.rewritePath("^/os", "")
                                        .filter(authenticationFilter.apply(new AuthenticationFilter.Config()))
                                )
                                .uri("lb://ORDERSERVICE")
                ).route("todos",
                        r -> r.path("/todos/**")
                                .filters(f ->
                                        f.circuitBreaker(config ->
                                                config.setName("todos")
                                                .setFallbackUri("forward:/fallback/todos"))
                                )
                                .uri("https://dummyjson.com")
                )
                .build();
    }
}
