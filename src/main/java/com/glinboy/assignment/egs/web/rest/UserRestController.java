package com.glinboy.assignment.egs.web.rest;

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

}
