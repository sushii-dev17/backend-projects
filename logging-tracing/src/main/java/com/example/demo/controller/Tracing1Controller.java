package com.example.demo.controller;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class Tracing1Controller {

    @Autowired
    Tracer tracer;

    @Autowired
    private RestClient restClient;

    @GetMapping("/api")
    public String invokeTracingApp1() {

        String response = restClient.get().uri("http://localhost:8082/api/hello2")
                .retrieve()
                .body(String.class);

        return "response from app2: " + response;

    }


    @GetMapping("/api/paymnet")
    public String productservice() {

        //call cart//call orders
        String reponse = cartservice();
        return "response from app2: " +reponse;

    }

    public String cartservice() {
        //card enhancement
        return "hello from tracing app 2!!";
    }

    @GetMapping("/api/test/span")
    public String manualSpanTest() {

        Span parentSpan = tracer.currentSpan();

        Span childSpan = tracer.nextSpan(parentSpan).name("manual-child-span");

        childSpan.start();

        Tracer.SpanInScope spanInScope = tracer.withSpan(childSpan);

        try {
            Thread.sleep(70);
        } catch (Exception e) {

        } finally {
            if (spanInScope != null) {
                spanInScope.close();
            }
            childSpan.end();
        }
        return "response from trace-app1";
    }

}

