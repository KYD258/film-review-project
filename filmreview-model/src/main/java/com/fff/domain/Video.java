package com.fff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "video")
public class Video implements Serializable {
    @Id
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
    @Column(name = "video_show_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date videoShowTime;
    @Column(name = "video_length")
    private Integer videoLength;
    @Column(name = "video_type")
    private String videoType;
    @Column(name = "visible")
    private Integer visible;
    @Column(name = "video_pic")
    private String videoPic;
    @Column(name = "show_index")
    private Integer showIndex;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
    @Column(name = "collection_orsubscription")
    private Integer collectionOrsubscription;
    @Column(name = "video_status")
    private Integer videoStatus;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "classify")
    private String classify;


}
