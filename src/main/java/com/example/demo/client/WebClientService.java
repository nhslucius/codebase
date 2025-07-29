package com.example.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpHeaders;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final WebClient webClient;

    public <T> Mono<T> get(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> post(String url, Object requestBody, Class<T> responseType) {
        return webClient.post()
                .uri(url)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> put(String url, Object requestBody, Class<T> responseType) {
        return webClient.put()
                .uri(url)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> delete(String url, Class<T> responseType) {
        return webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType);
    }

    // ----- Advanced methods with headers -----

    public <T> Mono<T> getWithHeaders(String url, Map<String, String> headers, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .headers(httpHeaders -> setHeaders(httpHeaders, headers))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> postWithHeaders(String url, Object requestBody, Map<String, String> headers, Class<T> responseType) {
        return webClient.post()
                .uri(url)
                .headers(httpHeaders -> setHeaders(httpHeaders, headers))
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> putWithHeaders(String url, Object requestBody, Map<String, String> headers, Class<T> responseType) {
        return webClient.put()
                .uri(url)
                .headers(httpHeaders -> setHeaders(httpHeaders, headers))
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> deleteWithHeaders(String url, Map<String, String> headers, Class<T> responseType) {
        return webClient.delete()
                .uri(url)
                .headers(httpHeaders -> setHeaders(httpHeaders, headers))
                .retrieve()
                .bodyToMono(responseType);
    }

    private void setHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {
        headers.forEach(httpHeaders::add);
    }
}
