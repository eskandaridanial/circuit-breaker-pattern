package com.resilience.service;

import com.resilience.model.Request;
import com.resilience.model.Response;
import org.springframework.stereotype.Service;

@Service
public class ResilienceServiceImpl implements ResilienceService {

    @Override
    public Response process(Request request, Integer wait) throws InterruptedException {
        if (null != wait)
            Thread.sleep(wait);
        Response response = new Response();
        response.setValue(request.getValue());
        response.setLen(request.getValue().length());
        return response;
    }
}
