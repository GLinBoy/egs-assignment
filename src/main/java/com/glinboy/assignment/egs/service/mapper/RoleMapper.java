package com.glinboy.assignment.egs.service.mapper;

import org.mapstruct.Mapper;

import com.glinboy.assignment.egs.model.Role;
import com.glinboy.assignment.egs.service.dto.RoleDTO;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends GenericMapper<RoleDTO, Role> {

}
