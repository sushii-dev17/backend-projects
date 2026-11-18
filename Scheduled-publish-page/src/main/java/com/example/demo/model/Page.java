package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL)
    private List<PageVersion> pageVersion = new ArrayList<>();
}
