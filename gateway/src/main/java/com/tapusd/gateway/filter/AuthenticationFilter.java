package com.tapusd.gateway.filter;

import com.tapusd.gateway.exception.AuthorizationException;
import com.tapusd.gateway.service.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final List<String> OPEN_API_LIST = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/eureka");

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (isSecuredPath(request)) {
                String authorization = request.getHeaders().getFirst("Authorization");
                if (!StringUtils.hasText(authorization)) {
                    throw new AuthorizationException("Authorization token not found!");
                }
                if (!authorization.startsWith("Bearer")) {
                    throw new IllegalArgumentException("Invalid bearer token format.");
                }

                String jwtToken = authorization.substring("Bearer ".length());

                try {
                    if(!jwtService.isValidToken(jwtToken)) {
                        throw new AuthorizationException("Invalid authorization token!");
                    }
                } catch (Exception ex) {
                    throw new AuthorizationException("Unable to access customer service!", ex);
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

    private boolean isSecuredPath(ServerHttpRequest request) {
        return !OPEN_API_LIST.contains(request.getURI().getPath());
    }
}
