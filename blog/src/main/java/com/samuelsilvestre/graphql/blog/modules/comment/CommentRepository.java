package com.samuelsilvestre.graphql.blog.modules.comment;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository <CommentEntity, UUID>{
    
}
