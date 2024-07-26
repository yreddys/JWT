package com.reddy.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddy.jwt.entity.user;
import com.reddy.jwt.model.AuthRequest;
import com.reddy.jwt.service.JwtService;
import com.reddy.jwt.service.MyUserDetailsService;

@RestController
@RequestMapping("/api")
public class userController {
	@Autowired
	private MyUserDetailsService service;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authenticationManger;
	@Autowired
	private JwtService jwt;

	@PostMapping("/register")
	String registerUser(@RequestBody user user) {
		String encodePwd = encoder.encode(user.getUpwd());
		user.setUpwd(encodePwd);
		boolean saveUser = service.saveUser(user);

		if (saveUser) {
			return "User Registered";

		} else {
			return "registration failed";
		}
	}

	@PostMapping("/login")
	String userAuthentication(@RequestBody AuthRequest request) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUname(),
				request.getUpwd());
		try {
			Authentication auth = authenticationManger.authenticate(token);
			if (auth.isAuthenticated()) {
				return jwt.generateToken(request.getUname());
//				return jwtToken;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "Authentication Failed";
	}

	@GetMapping("/welcome")
	String getWelcome() {
		return "Welcome Reddy";
	}

	@GetMapping("/greet")
	String getGreet() {
		return "greet";
	}
}
