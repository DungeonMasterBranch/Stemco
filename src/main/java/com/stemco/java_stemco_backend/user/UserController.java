package com.stemco.java_stemco_backend.user;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/testFilter")
	public String testFilter() {
		return "itsOK";
	}

	@GetMapping("/test")
	public void testServiceMethod() throws ExecutionException, InterruptedException, FirebaseAuthException, IOException {
		userService.testQueryMethod();
	}

	@PostMapping("/create")
	public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
		return userService.createUser(user);
	}

	@GetMapping("/get")
	public User getUser(@RequestParam String email) throws ExecutionException, InterruptedException {
		return userService.getUser(email);
	}

	@PutMapping("/update")
	public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
		return userService.updateUser(user);
	}

	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam String email) throws ExecutionException, InterruptedException {
		return userService.deleteUser(email);
	}
}
