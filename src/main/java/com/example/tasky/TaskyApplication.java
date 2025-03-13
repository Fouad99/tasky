package com.example.tasky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TaskyApplication.class, args);
		System.out.println("Hello World!");
	}
}
