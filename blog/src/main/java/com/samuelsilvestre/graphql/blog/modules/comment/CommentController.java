package com.samuelsilvestre.graphql.blog.modules.comment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.samuelsilvestre.graphql.blog.modules.post.PostEntity;
import com.samuelsilvestre.graphql.blog.modules.post.PostRepository;

@Controller 
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
private PostRepository postRepository;


    @MutationMapping
    CommentEntity addComment (@Argument ("comment") CommentInput commentInput){
        PostEntity post = postRepository.findById(commentInput.postId).orElse(null);
if (post == null) {
    throw new RuntimeException("Post not found");
}
        return this.commentRepository.save(new CommentEntity(commentInput.content, commentInput.date, commentInput.author, post));
    }

    @MutationMapping
    boolean deleteComment (@Argument ("commentId") UUID commentId){
        if (commentRepository.existsById(commentId)){
            commentRepository.deleteById(commentId);
            return true;
        }

        return false;
    }

    
    @QueryMapping
    Iterable<CommentEntity> comments(){
        return this.commentRepository.findAll();
    }

    record CommentInput(String content, String date, String author, UUID postId){

    }

}
