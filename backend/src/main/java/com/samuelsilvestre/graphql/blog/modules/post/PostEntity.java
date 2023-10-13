package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.samuelsilvestre.graphql.blog.modules.comment.CommentEntity;
import com.samuelsilvestre.graphql.blog.modules.user.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "POSTS")
public class PostEntity {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String content;
    private String date;
    private String author;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name= "user_id")
    private UUID userId;

    public PostEntity(String title, String content, String date, String author, UUID userId) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.userId = userId;

    }
    public UUID getUserId() {
        return user.getId();
    }

     @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();
}
