package com.example.demo.dto;

import com.example.demo.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageVersionRequest {

    private String url;

    private String moduleJson;

    private Status status;

    private LocalDateTime publishAt;

    private LocalDateTime publishUntil;


}
