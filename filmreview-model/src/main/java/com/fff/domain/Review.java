package com.fff.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;
    @Column(name = "review_content")
    private String reviewContent;
    @Column(name = "review_create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reviewCreateTime;
    @Column(name = "video_id")
    private Integer videoId;
    @Column(name = "review_grade")
    private Integer reviewGrade;

}
