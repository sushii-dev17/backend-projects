package com.example.demo.service;

import com.example.demo.dto.PageVersionRequest;
import com.example.demo.model.Page;
import com.example.demo.model.PageVersion;
import com.example.demo.model.Status;
import com.example.demo.repostory.PageRepository;
import com.example.demo.repostory.PageVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    private final PageVersionRepository pageVersionRepo;


    public void createPageVersion(PageVersionRequest request){
        Optional<Page> byUrl = pageRepository.findByUrl(request.getUrl());
        Page page = byUrl.orElseGet(() -> {
            Page p = new Page();
            p.setUrl(request.getUrl());
            return pageRepository.save(p);
        });
        System.out.println("get the page if exists:............");
        PageVersion version = new PageVersion();
        version.setPage(page);
        version.setModules(request.getModuleJson());
        version.setStatus(request.getStatus());
        version.setPublishedAt(request.getPublishAt());
        version.setPublishUntil(request.getPublishUntil());

        pageVersionRepo.save(version);
    }
    public List<String> getContentByUrl(String url){

      //  List<String> data = new ArrayList<>();
       // Page page =pageRepository.findByUrl(url).orElseThrow(()->new RuntimeException("Page not found"));
        //in url home i have many page versions i want to display thoese whose status is published

        //and whose published time is between  current tile and

        return pageRepository.fetchPageWithActiveVersions(url,LocalDateTime.now());
    }

    @Transactional
    public void publishScheduledPages() {

        List<PageVersion> byStatusAndPublishedAtBefore = pageVersionRepo.findByStatusAndPublishedAtBefore(Status.SCHEDULED, LocalDateTime.now());

        for (PageVersion version : byStatusAndPublishedAtBefore) {

            version.setStatus(Status.PUBLISHED);
            pageVersionRepo.save(version);
        }
    }

        @Transactional
        public void makePagesExpiredPages(){

            List<PageVersion> expire = pageVersionRepo.findByStatusAndPublishUntilBefore(Status.PUBLISHED,LocalDateTime.now());

            for(PageVersion version:expire){

                version.setStatus(Status.EXPIRED);
                pageVersionRepo.save(version);
            }


    }

}
