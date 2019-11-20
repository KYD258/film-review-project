package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_review")
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "review_id")
    private Integer reviewId;
}
