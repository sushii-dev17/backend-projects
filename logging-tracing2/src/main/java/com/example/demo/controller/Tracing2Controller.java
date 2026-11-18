package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tracing2Controller {

    @GetMapping("/api/hello2")
    public String invokeTracingApp2(){

        return "hello from tracing app 2!!";

    }

}

