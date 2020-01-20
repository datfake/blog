package com.ncc.service.mapper;

import com.ncc.domain.*;
import com.ncc.service.dto.PostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "category.id", target = "categoryId")
    PostDTO toDto(Post post);

    @Mapping(source = "categoryId", target = "category")
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
