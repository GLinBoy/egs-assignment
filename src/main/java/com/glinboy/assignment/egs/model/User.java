package com.glinboy.assignment.egs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "EGS_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }, name = "UNQ_USER_EMAIL") })
public class User extends BaseEntity {

	@NotBlank
	@Size(max = 40)
	private String firstname;

	@NotBlank
	@Size(max = 40)
	private String lastname;

	@NaturalId
	@NotBlank
	@Size(max = 100)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	@NotNull
	private Boolean blocked;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "EGS_USER_ROLES",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}
