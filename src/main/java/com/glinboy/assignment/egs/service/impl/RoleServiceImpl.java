package com.glinboy.assignment.egs.service.impl;

import org.springframework.stereotype.Service;

import com.glinboy.assignment.egs.model.Role;
import com.glinboy.assignment.egs.repository.RoleRepository;
import com.glinboy.assignment.egs.service.RoleServiceApi;
import com.glinboy.assignment.egs.service.dto.RoleDTO;
import com.glinboy.assignment.egs.service.mapper.RoleMapper;

@Service
public class RoleServiceImpl
	extends AbstractServiceImpl<RoleDTO, Role, RoleRepository, RoleMapper>
	implements RoleServiceApi {

	public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
		super(repository, mapper);
	}

}
