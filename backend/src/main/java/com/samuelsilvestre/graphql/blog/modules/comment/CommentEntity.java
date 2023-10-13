package com.samuelsilvestre.graphql.blog.modules.comment;

import java.util.UUID;

import com.samuelsilvestre.graphql.blog.modules.post.PostEntity;
import com.samuelsilvestre.graphql.blog.modules.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COMMENTS")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    private String date;
    private String author;
    

    @ManyToOne
    @JoinColumn(name = "post_id",insertable = false, updatable = false)
    private PostEntity post;

    @Column(name= "post_id")
    private UUID postId;





    public CommentEntity (String content, String date, String author, PostEntity post){
        this.content = content;
        this.date = date;
        this.author = author;
        this.post = post;
    }

    public UUID getPostId() {
        if (this.post != null) {
            return this.post.getId();
        }
        return null;
    }
}
