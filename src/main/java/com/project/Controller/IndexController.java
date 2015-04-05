package com.project.Controller;

import com.project.Controller.exception.handler.UserErrorMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Service.UserService;
import com.project.Entities.UserMstr;

@Controller
@RequestMapping("/")
public class IndexController {
	final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	private static UserService userService;
	
	@Autowired	
	AuthenticationManager authenticationManager;
	
	@Autowired
	public void setUserService(UserService userService) {
		IndexController.userService = userService;
	}

	@RequestMapping
	public String index(){		
		return "index";		
	}
		
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserMstr> login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserMstr user = userService.findUserByEmail(username);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication auth = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		if(auth.isAuthenticated()){
			logger.debug("User {} logged in successfully...", auth.getName());
			return new ResponseEntity<UserMstr>(user, HttpStatus.OK);				
		}
		return new ResponseEntity<UserMstr>(user, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserMstr> createUser(@RequestBody UserMstr user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		UserMstr savedUser = userService.create(user);
		logger.debug("User {} created successfully...", savedUser.getEmailID());
		return new ResponseEntity<UserMstr>(savedUser, HttpStatus.CREATED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseBody
	public ResponseEntity<UserErrorMessage> handleError(HttpServletRequest request,
			Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised "
				+ exception);
		UserErrorMessage response = new UserErrorMessage();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(exception.getMessage());

		return new ResponseEntity<UserErrorMessage>(response, HttpStatus.UNAUTHORIZED);
	}

}
