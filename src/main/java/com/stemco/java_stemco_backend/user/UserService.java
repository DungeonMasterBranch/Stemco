package com.stemco.java_stemco_backend.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

	public void testQueryMethod() throws ExecutionException, InterruptedException, FirebaseAuthException, IOException {
//		Firestore firestore = FirestoreClient.getFirestore();
//		UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//				.setEmail("aibarelemesov7@gmail.com")
//				.setEmailVerified(false)
//				.setPassword("123456")
//				.setDisabled(false);
//		UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//
//		CollectionReference users = firestore.collection("users");
//		Query query = users.whereEqualTo("first_name","Aibar");
//		ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
//
//		for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
//			log.info((String) document.get("password"));
//		}


		StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
		InputStream testFile = new FileInputStream("C://Users//aibar//Desktop//Trash//sea.png");
		String blobString = "NEW_FOLDER/" + "FILE_NAME";

		storageClient.bucket().create(blobString, testFile , Bucket.BlobWriteOption.userProject("stemcodb"));
	}

	public String createUser(User user) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		if (getUser(user.getEmail()) == null) {
			ApiFuture<WriteResult> collectionsApiFuture = fireStore.collection("users").document(user.getEmail()).set(user);
			return "User have been created";
		} else {
			return "User with email " + user.getEmail() + " already exists";
		}
	}

	public User getUser(String email) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = fireStore.collection("users").document(email);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		User user;
		if (document.exists()){
			user = document.toObject(User.class);
			return user;
		}
		return null;
	}

	public String updateUser(User user) throws ExecutionException, InterruptedException {
		Firestore fireStore = FirestoreClient.getFirestore();
		if (getUser(user.getEmail()) != null){
			ApiFuture<WriteResult> collectionsApiFuture = fireStore.collection("users").document(user.getEmail()).set(user);
			//return collectionsApiFuture.get().getUpdateTime().toString();
			return "User have been updated";
		} else {
			return "User with email " + user.getEmail() + " doesnt exists";
		}
	}

	public String deleteUser(String email){
		Firestore fireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResultApiFuture = fireStore.collection("users").document(email).delete();
		return "Successfully deleted user with email " + email;
	}
}
