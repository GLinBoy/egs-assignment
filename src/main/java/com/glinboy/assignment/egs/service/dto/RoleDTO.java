package com.glinboy.assignment.egs.service.dto;

import com.glinboy.assignment.egs.model.RoleName;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleDTO extends BaseDTO {

	@NotNull
	private RoleName name;

}
