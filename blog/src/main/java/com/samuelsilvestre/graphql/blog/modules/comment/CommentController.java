package com.samuelsilvestre.graphql.blog.modules.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller 
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @MutationMapping
    CommentEntity addComment (@Argument CommentInput commentInput){
        return this.commentRepository.save(new CommentEntity(commentInput.content, commentInput.date, commentInput.author));
    }

    @QueryMapping
    Iterable<CommentEntity> comment(){
        return this.commentRepository.findAll();
    }

    record CommentInput(String content, String date, String author){

    }

}
