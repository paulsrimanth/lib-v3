package com.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.AllUsers;
import com.library.repository.AllUsersRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UsersController {
	
	private AllUsersRepository alluserrepo;
	private final PasswordEncoder passwordEncoder;

	public UsersController(AllUsersRepository alluserrepo, PasswordEncoder passwordEncoder) {
		super();
		this.alluserrepo = alluserrepo;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/register")
    public ResponseEntity<AllUsers> register(@RequestBody AllUsers request) {
		AllUsers user = new AllUsers();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(request.getRoles());

        return ResponseEntity.ok(alluserrepo.save(user));
    }
	
	 @PostMapping("/{adminId}/users")
	    public ResponseEntity<AllUsers> createUserByAdmin(@PathVariable Integer adminId, @RequestBody AllUsers user) {
		 AllUsers admin = alluserrepo.findById(adminId).orElseThrow();
	     System.out.println(admin.getUsers());
		 AllUsers newUser = new AllUsers();
				 //userService.createUser(user);
		 newUser.setEmail(user.getEmail());
		 newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		 newUser.setRoles(user.getRoles());
		 AllUsers usernew = alluserrepo.save(newUser);
	        admin.getUsers().add(usernew);
	        alluserrepo.save(admin);
//	        userService.saveUser(admin);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(usernew);
	        		//(alluserrepo.save(newUser));
	    }
	


}
