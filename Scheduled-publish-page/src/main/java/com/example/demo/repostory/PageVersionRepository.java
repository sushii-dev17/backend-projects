package com.example.demo.repostory;

import com.example.demo.model.PageVersion;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PageVersionRepository extends JpaRepository<PageVersion, Long> {

    List<PageVersion> findByPageIdAndStatusAndPublishedAtBeforeAndPublishUntilAfter(Long pageId, Status status,LocalDateTime publishAt,LocalDateTime publishUntil);
    List<PageVersion> findByStatusAndPublishedAtBefore(Status status, LocalDateTime time);

    List<PageVersion> findByStatusAndPublishUntilBefore(Status status,LocalDateTime now);
}
