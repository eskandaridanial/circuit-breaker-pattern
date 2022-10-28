package com.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class Application {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CircuitBreaker circuitBreaker() {
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
				.custom()
				// time based
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
				.minimumNumberOfCalls(3)
				.slidingWindowSize(10)
				.failureRateThreshold(70.0f)
				// count based
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(10)
				.slowCallRateThreshold(80.0f)
				.slowCallDurationThreshold(Duration.ofSeconds(2))
				// hal-open state configuration
				.waitDurationInOpenState(Duration.ofSeconds(10))
				.permittedNumberOfCallsInHalfOpenState(2)
				.build();

		CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

		return circuitBreakerRegistry.circuitBreaker("server");
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
