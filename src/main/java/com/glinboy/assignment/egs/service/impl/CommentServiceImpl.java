package com.glinboy.assignment.egs.service.impl;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.glinboy.assignment.egs.model.Comment;
import com.glinboy.assignment.egs.model.Product;
import com.glinboy.assignment.egs.model.RoleName;
import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.repository.CommentRepository;
import com.glinboy.assignment.egs.service.CommentServiceApi;
import com.glinboy.assignment.egs.service.dto.CommentDTO;
import com.glinboy.assignment.egs.service.mapper.CommentMapper;
import com.glinboy.assignment.egs.util.SecurityUtils;

@Service
public class CommentServiceImpl
	extends AbstractServiceImpl<CommentDTO, Comment, CommentRepository, CommentMapper>
	implements CommentServiceApi {

	private final EntityManager em;

	public CommentServiceImpl(CommentRepository repository,
			CommentMapper mapper,
			EntityManager em) {
		super(repository, mapper);
		this.em = em;
	}
	
	@Override
	public CommentDTO save(CommentDTO commentDTO) {
		Comment comment = this.mapper.toEntity(commentDTO);
		comment.setUser(em.getReference(User.class, SecurityUtils.getCurrentUserPrincipal().getId()));
		comment.setProduct(em.getReference(Product.class, commentDTO.getProductId()));
		comment = this.repository.save(comment);
		return this.mapper.toDto(comment);
	}

	@Override
	public Page<CommentDTO> getAll(Long productId, Pageable pageable) {
		Page<Comment> page = this.repository.findAllByProductIdAndContentNotNull(productId, pageable);
		return page.map(mapper::toDto);
	}

	@Override
	public CommentDTO update(CommentDTO commentDTO) {
		CommentDTO cmfound = this.findById(commentDTO.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find any resource for: " + commentDTO.getId()));
		if(!cmfound.getProductId().equals(commentDTO.getProductId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment Does'nt blong to this product.");
		}
		if(!SecurityUtils.isCurrentUserInRole(RoleName.ROLE_ADMIN) &&
				!cmfound.getUserId().equals(SecurityUtils.getCurrentUserPrincipal().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment Does'nt blong to current user.");
		}
		return save(commentDTO);
	}

	@Override
	public void deleteSingleById(Long productId, Long id) {
		if(SecurityUtils.isCurrentUserInRole(RoleName.ROLE_ADMIN)) {
			this.deleteSingleById(id);
		} else {
			this.repository.deleteByIdAndProductIdAndUserId(id, productId, SecurityUtils.getCurrentUserPrincipal().getId());
		}
	}

}
