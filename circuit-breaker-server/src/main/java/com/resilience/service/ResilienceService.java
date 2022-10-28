package com.resilience.service;

import com.resilience.model.Request;
import com.resilience.model.Response;

public interface ResilienceService {

    Response process(Request request , Integer wait) throws InterruptedException;

}
