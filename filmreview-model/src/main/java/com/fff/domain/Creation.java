package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "creation")
public class Creation {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "creation_id")
    private Integer creationId;
    @Column(name = "creation_pic")
    private String creationPic;
    @Column(name = "article")
    private String article;
    @Column(name = "creation_status")
    private Integer creationStatus;
}
