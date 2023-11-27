package com.shop.sport;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Nho Nam API", version = "2.0", description = "Nho Nam Trying"))
public class SportApplication {


	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions
				.builder()
				.setCredentials(googleCredentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "Main_App_Sport");
		return FirebaseMessaging.getInstance(app);
	}

	public static void main(String[] args) {


		SpringApplication.run(SportApplication.class, args);
		System.out.println("===== Swagger api By Nho Nam ======");
		System.err.println("http://localhost:8080/swagger-ui/index.html");
		System.err.println("/v3/api-docs");
		System.out.println("===== Swagger api By Nho Nam ======");
	}

}
