package com.example.demo.config;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .filter(logRequest())
                .filter(logResponse())
                .filter(addTraceId())
                .build();
    }

    private ExchangeFilterFunction addTraceId() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            String traceId = MDC.get("traceId");
            if (traceId == null) {
                traceId = UUID.randomUUID().toString();
                MDC.put("traceId", traceId);
            }
            return Mono.just(
                    ClientRequest.from(clientRequest)
                            .header("X-Trace-Id", traceId)
                            .build()
            );
        });
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            String traceId = MDC.get("traceId");
            System.out.println("➡️ [TRACE_ID: " + traceId + "] Request: " + request.method() + " " + request.url());
            return Mono.just(request);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            String traceId = MDC.get("traceId");
            System.out.println("⬅️ [TRACE_ID: " + traceId + "] Response Status: " + response.statusCode());
            return Mono.just(response);
        });
    }


}
