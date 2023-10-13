package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, UUID>{
    
}
