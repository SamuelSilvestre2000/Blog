package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.samuelsilvestre.graphql.blog.modules.comment.CommentEntity;
import com.samuelsilvestre.graphql.blog.modules.comment.CommentRepository;
import com.samuelsilvestre.graphql.blog.modules.user.UserEntity;
import com.samuelsilvestre.graphql.blog.modules.user.UserRepository;



@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;



    @MutationMapping()
    PostEntity addPost(@Argument ("post") PostInput post){
        return this.postRepository.save(new PostEntity(post.title, post.content, post.date, post.author, post.userId));
    }
    
//     @MutationMapping()
//     PostEntity addPost(@Argument PostInput post, @Argument UUID userId){
//     UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//     PostEntity newPost = new PostEntity(post.title, post.content, post.date, post.author);
//     newPost.setUser(user);
//     return this.postRepository.save(newPost);
// }

    @QueryMapping()
    Optional<PostEntity> postById(@Argument UUID id){
        return this.postRepository.findById(id);
    }

    @QueryMapping
    Iterable<PostEntity> posts(){
        return this.postRepository.findAll();
    }


    @QueryMapping
    public PostWithCommentsDTO getPostWithComments(@Argument("id") UUID id) {
        Optional<PostEntity> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();
            List<CommentEntity> comments = commentRepository.findByPostId(id);
            return new PostWithCommentsDTO(post, comments);
        }
        return null;
    }

    private List<CommentEntity> getCommentsByPostId(UUID postId) {
        return commentRepository.findByPostId(postId);
    }

    @MutationMapping
    boolean deletePost(@Argument("postId") UUID postId) {
    if (postRepository.existsById(postId)) {
        // Delete associated comments first
        commentRepository.deleteByPostId(postId);
        
        // Delete the post
        postRepository.deleteById(postId);
        return true;
    }
    return false;
}
    public List<CommentEntity> comments(PostEntity post) {
    return commentRepository.findByPostId(post.getId());
}


    record PostInput(String title, String content, String date, String author, UUID userId){

    }
}
