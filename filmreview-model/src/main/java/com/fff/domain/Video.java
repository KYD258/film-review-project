package com.fff.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "video")
public class Video {
    @javax.persistence.Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;
    @Column(name = "video_name")
    private String videoName;
    @Column(name = "video_director")
    private String videoDirector;
    @Column(name = "video_role")
    private String videoRole;
    @Column(name = "video_producer")
    private String videoProducer;
    @Column(name = "video_review")
    private String videoReview;
    @Column(name = "video_grade")
    private Integer videoGrade;
    @Column(name = "video_language")
    private String videoLanguage;
    @Column(name = "video_showTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date videoShowTime;
    @Column(name = "video_length")
    private Integer videoLength;
    @Column(name = "video_type")
    private String videoType;
    @Column(name = "visible")
    private Integer visible;
    @Column(name = "video_pic")
    private String videoPic;
    @Column(name = "showIndex")
    private Integer showIndex;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    @Column(name = "collectionOrsubscription")
    private Integer collectionOrsubscription;
    @Column(name = "video_status")
    private Integer videoStatus;


}
