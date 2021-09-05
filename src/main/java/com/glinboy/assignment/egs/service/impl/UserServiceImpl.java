package com.glinboy.assignment.egs.service.impl;

import org.springframework.stereotype.Service;

import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.repository.UserRepository;
import com.glinboy.assignment.egs.service.UserServiceApi;
import com.glinboy.assignment.egs.service.dto.UserDTO;
import com.glinboy.assignment.egs.service.mapper.UserMapper;

@Service
public class UserServiceImpl
	extends AbstractServiceImpl<UserDTO, User, UserRepository, UserMapper>
	implements UserServiceApi{

	public UserServiceImpl(UserRepository repository, UserMapper mapper) {
		super(repository, mapper);
	}

}
