package com.learn.api_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class CustomeFilter implements GatewayFilter {
   Logger log =  LoggerFactory.getLogger(CustomeFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String val = exchange.getRequest().getHeaders().getFirst("X_USER_ID");
		log.info("authorizaion="+val);
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
		   log.info("response code="+exchange.getResponse().getStatusCode());
		}));
	}
}
