package com.glinboy.assignment.egs.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.assignment.egs.service.UserServiceApi;
import com.glinboy.assignment.egs.service.dto.UserDTO;

@RestController
@RequestMapping(path = "/api/users")
public class UserRestController extends GenericRestController<UserDTO, UserServiceApi> {

	public UserRestController(UserServiceApi service) {
		super(service);
	}

	@PostMapping("/{id}/block")
	public ResponseEntity<Void> blockUser(@PathVariable Long id) {
		this.service.block(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/unblock")
	public ResponseEntity<Void> unblockUser(@PathVariable Long id) {
		this.service.unblock(id);
		return ResponseEntity.ok().build();
	}
}
