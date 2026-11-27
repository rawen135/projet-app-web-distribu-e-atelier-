package org.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("CANDIDAT", route -> route
                        .path("/candidat", "/candidat/**")
                        .filters(filters -> filters.rewritePath(
                                "/candidat(?<remaining>/?.*)",
                                "/candidat/api/candidats${remaining}"))
                        .uri("http://localhost:8081"))
                .route("JOB", route -> route
                        .path("/jobs", "/jobs/**")
                        .filters(filters -> filters.rewritePath(
                                "/jobs(?<remaining>/?.*)",
                                "/api/jobs${remaining}"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
