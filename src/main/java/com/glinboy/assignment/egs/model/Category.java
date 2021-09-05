package com.glinboy.assignment.egs.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "EGS_CATEGORY", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }, name = "UNQ_CATEGORY_TILE") })
public class Category extends BaseEntity {

	@NotBlank
	@Size(max = 40)
	private String title;

}
