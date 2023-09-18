package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;



@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @MutationMapping()
    PostEntity addPost(@Argument PostInput post){
        return this.postRepository.save(new PostEntity(post.title, post.content, post.date, post.author));
    }
    
    @QueryMapping()
    Optional<PostEntity> postById(@Argument UUID id){
        return this.postRepository.findById(id);
    }

    record PostInput(String title, String content, String date, String author){

    }
}
