package com.glinboy.assignment.egs.service;

import com.glinboy.assignment.egs.service.dto.UserDTO;

public interface UserServiceApi extends GenericService<UserDTO> {

	void block(Long id);

	void unblock(Long id);

}
