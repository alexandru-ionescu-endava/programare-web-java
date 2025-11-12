package com.example.proxy;

import com.example.model.ExternalProductResponse;
import com.example.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DummyJsonProxy {

    private final RestClient restClient;

    public List<Product> getProducts() {
        var proxyResponse = restClient.get()
                .uri("/products")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RuntimeException("Client error when fetching products");
                })
                .body(ExternalProductResponse.class);
        return proxyResponse.getProducts();
    }

}
