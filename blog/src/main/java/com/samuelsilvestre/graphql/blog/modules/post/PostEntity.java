package com.samuelsilvestre.graphql.blog.modules.post;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String content;
    private String date;
    private String author;

    public PostEntity(String title, String content, String date, String author) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;

    }
}
