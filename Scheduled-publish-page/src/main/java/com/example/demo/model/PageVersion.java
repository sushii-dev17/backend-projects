package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PageVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @Lob
    private String modules;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime publishedAt;

    private LocalDateTime publishUntil;
}
