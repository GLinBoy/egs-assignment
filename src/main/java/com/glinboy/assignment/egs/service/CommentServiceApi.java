package com.glinboy.assignment.egs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.glinboy.assignment.egs.service.dto.CommentDTO;

public interface CommentServiceApi extends GenericService<CommentDTO> {

	Page<CommentDTO> getAll(Long productId, Pageable pageable);
	
	CommentDTO update(CommentDTO commentDTO);
	
	void deleteSingleById(Long productId, Long id);

}
