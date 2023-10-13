package com.samuelsilvestre.graphql.blog.modules.comment;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository <CommentEntity, UUID>{
    void deleteByPostId(UUID postId);
    
    //@Query("SELECT c FROM CommentEntity c WHERE c.postId = :postId")
    List<CommentEntity> findByPostId(UUID postId);
}
    