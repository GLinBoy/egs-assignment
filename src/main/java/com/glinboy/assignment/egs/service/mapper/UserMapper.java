package com.glinboy.assignment.egs.service.mapper;

import org.mapstruct.Mapper;

import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.service.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends GenericMapper<UserDTO, User> {

}
