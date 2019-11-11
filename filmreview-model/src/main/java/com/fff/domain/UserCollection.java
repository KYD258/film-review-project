package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_collection")
public class UserCollection {
    @javax.persistence.Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "collection_id")
    private Integer collectionId;
}
