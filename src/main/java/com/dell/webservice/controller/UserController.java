package com.dell.webservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dell.webservice.entity.User;
import com.dell.webservice.repository.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getUsers(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		List<User> list = userService.getEntityUsers(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable("userId") int id) {
		return new ResponseEntity<Optional<User>>(this.userService.getEntityUser(id),new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody(required = false) User addUser){
		this.userService.addEntityUser(addUser);
		return new ResponseEntity<User>(addUser, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") int id, @RequestBody(required = false) User updateUser) {
		if(id != updateUser.getId()) {
			return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		this.userService.updateEntityUser(updateUser);
		return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<User> deleteProduct(@PathVariable("userId") int id){
		Object getUser = this.userService.getEntityUser(id);
		if(getUser == null) {
			return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		else {
			this.userService.deleteEntityUser(id);
			return new ResponseEntity<User>(new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
		
	}
	

}
