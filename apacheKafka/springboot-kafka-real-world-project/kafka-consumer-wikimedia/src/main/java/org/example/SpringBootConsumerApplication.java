package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerApplication.class);
    }

    /*@Override
    public void run(String... args) throws Exception {
        WikimediaData wiki = new WikimediaData();
        wiki.setTexts("hello");
        wikimediaDataRepository.save(wiki);
    }*/
}
