package com.glinboy.assignment.egs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glinboy.assignment.egs.model.Role;
import com.glinboy.assignment.egs.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);

}
