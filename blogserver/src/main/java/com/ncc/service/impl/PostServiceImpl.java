package com.ncc.service.impl;

import com.ncc.domain.Category;
import com.ncc.repository.CategoryRepository;
import com.ncc.service.PostService;
import com.ncc.domain.Post;
import com.ncc.repository.PostRepository;
import com.ncc.service.dto.PostDTO;
import com.ncc.service.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Post}.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    /**
     * Save a post.
     *
     * @param postDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PostDTO save(PostDTO postDTO) {
        log.debug("Request to save Post : {}", postDTO);
        Category category = categoryRepository.findOneById(postDTO.getCategoryId());
        Post post = new Post();
        post.setCategory(category);
        post = postMapper.toEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postRepository.findAll(pageable)
            .map(postMapper::toDto);
    }

    @Override
    public Page<PostDTO> findAllByCategory(Pageable pageable, String tag) {
        Category category = categoryRepository.findOneByTag(tag);
        return postRepository.findAllByCategory(category,pageable)
            .map(postMapper::toDto);
    }

    /**
     * Get one post by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PostDTO> findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id)
            .map(postMapper::toDto);
    }

    /**
     * Delete the post by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }
}
