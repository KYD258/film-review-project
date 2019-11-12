package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Integer collectionId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "video_id")
    private Integer videoId;
}
