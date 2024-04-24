package com.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.AllUsers;
import com.library.model.AuthenticationResponse;
import com.library.repository.AllUsersRepository;
import com.library.service.AuthenticationService;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
	
	private AllUsersRepository alluserrepo;
	private AuthenticationService authService;

	public RoleController(AllUsersRepository alluserrepo, AuthenticationService authService) {
		super();
		this.alluserrepo = alluserrepo;
		this.authService = authService;
	}


	@GetMapping("/fetchAll")
	public ResponseEntity<AllUsers> getAllProducts() {
		System.out.println("in admin authorized");
		AllUsers users = alluserrepo.findById(4).orElseThrow();
		return ResponseEntity.ok(users);
	} 
	

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AllUsers request) {
    	System.out.println(request.getEmail());
        return ResponseEntity.ok(authService.authentice(request));
    }

}
