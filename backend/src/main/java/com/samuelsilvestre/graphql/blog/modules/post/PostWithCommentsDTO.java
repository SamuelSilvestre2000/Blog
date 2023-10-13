package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.List;

import com.samuelsilvestre.graphql.blog.modules.comment.CommentEntity;

public class PostWithCommentsDTO {
    private PostEntity post;
    private List<CommentEntity> comments;

    public PostWithCommentsDTO(PostEntity post, List<CommentEntity> comments) {
        this.post = post;
        this.comments = comments;
    }


}
