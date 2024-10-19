package com.upc.aventurape.platform.publication.domain.model.entities;

import com.upc.aventurape.platform.iam.infrastructure.security.SecurityUtils;
import com.upc.aventurape.platform.publication.domain.model.aggregates.Publication;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.CommentManager;
import com.upc.aventurape.platform.publication.domain.model.valueobjects.ProfileId;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Embedded
    private CommentManager commentManager;

    private String content;

    private Short rating;


    public Comment(){
        this.publication = new Publication();
        this.content = "";
        this.rating = 0;

    }
    public Comment(Publication publication, String content, Short rating) {
        this.publication = publication;
        this.content = content;
        this.rating = rating;
    }

    public Long AdventureId() {
        return  SecurityUtils.getCurrentUserId();
    }
}
