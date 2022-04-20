package com.stemco.java_stemco_backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

@Slf4j
@Configuration
public class FirebaseConfig {

	@Value("${firebase.databaseUrl}")
	String firebaseDatabaseUrl;

	@Value("firebase.storageBucketUrl")
	String firebaseStorageUrl;

	@Primary
	@Bean
	public FirebaseApp firebaseInit() throws IOException, FirebaseAuthException {
		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.getApplicationDefault())
				.setProjectId("stemcodb")
				.setDatabaseUrl(firebaseDatabaseUrl)
				.setStorageBucket("stemcodb.appspot.com")
				.build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
			log.info("connected to the firebase");
		}

		return FirebaseApp.getInstance();
	}

}
