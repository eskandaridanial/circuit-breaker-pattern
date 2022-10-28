package com.resilience.controller;

import com.resilience.model.Request;
import com.resilience.model.Response;
import com.resilience.service.ResilienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("api/resilience/server/v1/")
@RequiredArgsConstructor
public class ResilienceController {

    private final ResilienceService resilienceService;

    @PostMapping("len/{wait}")
    public ResponseEntity<Response> process(@RequestBody Request request , @PathVariable(required = false) Integer wait) throws InterruptedException {
        return new ResponseEntity<>(resilienceService.process(request, wait) , HttpStatus.OK);
    }
}
