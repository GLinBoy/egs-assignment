package com.glinboy.assignment.egs.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.glinboy.assignment.egs.model.Comment;
import com.glinboy.assignment.egs.service.dto.CommentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CommentMapper extends GenericMapper<CommentDTO, Comment> {

	@Mapping(source = "user.id", target = "userId")
	@Mapping(source = "product.id", target = "productId")
	CommentDTO toDto(Comment entity);

	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "productId", target = "product.id")
	Comment toEntity(CommentDTO dto);

}
