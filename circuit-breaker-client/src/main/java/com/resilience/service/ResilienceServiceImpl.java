package com.resilience.service;

import com.resilience.model.Request;
import com.resilience.model.Response;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResilienceServiceImpl implements ResilienceService {

    @Value("${resilience.server.len.address}")
    private String url;

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    @Override
    public Response process(Request request , Integer wait) throws Exception {
        log.info("state of circuit breaker is " + circuitBreaker.getState());

        Supplier<Response> supplier = circuitBreaker.decorateSupplier(() -> callServer(request , wait));

        Response response = Try.ofSupplier(supplier)
                .recover(throwable -> null)
                .get();

        if (null == response)
            throw new Exception("underlying service is not available at the moment");

        return response;
    }

    @Override
    public Response callServer(Request request , Integer wait) {
        return restTemplate.exchange(null != wait ? url + "/" + wait : url, HttpMethod.POST, new HttpEntity<>(request), Response.class).getBody();
    }
}
