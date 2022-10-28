package com.resilience.controller;

import com.resilience.model.MainModel;
import com.resilience.model.Request;
import com.resilience.model.Response;
import com.resilience.service.ResilienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/resilience/client/v1/")
@RequiredArgsConstructor
public class ResilienceController {

    private final ResilienceService resilienceService;

    @PostMapping("len/{wait}")
    public ResponseEntity<MainModel<Response>> process(@RequestBody Request request , @PathVariable(required = false) Integer wait) throws Exception {
        try {
            MainModel<Response> mainModel = new MainModel<>();
            mainModel.setCode(HttpStatus.OK.value());
            mainModel.setMessage("success");
            mainModel.setResult(resilienceService.process(request , wait));
            return new ResponseEntity<>(mainModel , HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
