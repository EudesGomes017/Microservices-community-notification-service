package com.Microservices_community_notification_service.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
		System.out.println("🚀 Notification Service está no ar e escutando Kafka...");
	}


}
