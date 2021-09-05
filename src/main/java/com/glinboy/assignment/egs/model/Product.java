package com.glinboy.assignment.egs.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
@Table(name = "EGS_PRODUCT", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "title" }, name = "UNQ_PRODUCT_TILE") })
public class Product extends BaseEntity {

	@NotBlank
	@Size(max = 40)
	private String title;

	@Size(max = 256)
	private String descriptiuon;

	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer = 6, fraction = 2)
	private BigDecimal price;

	@Size(max = 128)
	private String image;

	private Long rate = 0L;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", nullable = false,
		foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
	private Category category;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();
}
