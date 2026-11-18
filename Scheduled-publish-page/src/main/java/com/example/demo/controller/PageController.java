package com.example.demo.controller;

import com.example.demo.dto.PageVersionRequest;
import com.example.demo.service.PageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pages")
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService){
        this.pageService = pageService;
    }

    @PostMapping("/version")
    public ResponseEntity<String> createPageVersion(@RequestBody PageVersionRequest request) {

       pageService.createPageVersion(request);

        return  ResponseEntity.status(HttpStatus.CREATED).body("page is created");
    }

    @GetMapping("/content")
    public ResponseEntity<List<String>> getPageContent(
            @RequestParam String url) {

        List<String> contentByUrl = pageService.getContentByUrl(url);

        return new ResponseEntity<>(contentByUrl,HttpStatus.OK);

    }

    @GetMapping("/test")
    public String getPage(
            @RequestParam("url") String url) {

        return "hello world";

    }

}

