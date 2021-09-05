package com.glinboy.assignment.egs.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMENT", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "USER_ID", "PRODUCT_ID" }, name = "UNQ_COMMET_PRODUCT") })
public class Comment extends BaseEntity {

	@NotNull
	@Min(1) @Max(5)
	private Short rate;

	@NotBlank
	@Size(max = 256)
	private String content;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false,
		foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false,
		foreignKey = @ForeignKey(name = "FK_COMMENT_PRODUCT"))
	private Product product;

}
