package com.example.demo.repostory;

import com.example.demo.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findByUrl(String url);

    @Query("""
            SELECT v.modules FROM Page p
            JOIN p.pageVersion v
            WHERE p.url = :url
            AND v.status = 'PUBLISHED'
            AND v.publishedAt <= :now
            AND (v.publishUntil IS NULL OR v.publishUntil >= :now)
            """)
    List<String> fetchPageWithActiveVersions(String url, LocalDateTime now);
}
