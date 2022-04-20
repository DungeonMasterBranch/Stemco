package com.stemco.java_stemco_backend.security;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FirebaseIdTokenValidationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (request.getHeader("idToken") == null || request.getHeader("idToken").isEmpty()){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String idToken = request.getHeader("idToken");

		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
		try {
			firebaseAuth.verifyIdToken(idToken);
		} catch (FirebaseAuthException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		filterChain.doFilter(request,response);
	}
}
