package com.glinboy.assignment.egs.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.assignment.egs.service.RoleServiceApi;
import com.glinboy.assignment.egs.service.dto.RoleDTO;

@RestController
@RequestMapping(path = "/api/roles")
public class RoleRestController extends GenericRestController<RoleDTO, RoleServiceApi> {

	public RoleRestController(RoleServiceApi service) {
		super(service);
	}

}
