package com.glinboy.assignment.egs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.glinboy.assignment.egs.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Page<Comment> findAllByProductId(Long productId, Pageable pageable);

	@Modifying
	void deleteByIdAndProductIdAndUserId(Long id, Long productId, Long userId);

	Page<Comment> findAllByProductIdAndContentNotNull(Long productId, Pageable pageable);

}
