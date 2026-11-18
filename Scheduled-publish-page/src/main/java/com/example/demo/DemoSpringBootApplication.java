package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringBootApplication {

	/*@Autowired
	private A a;*/
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {

		a.show();
		a.show();
		a.show();
	}*/
}
