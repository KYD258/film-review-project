package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "password")
    private String passWord;
    private Integer age;
    private String gender;
    @Column(name = "user_pic")
    private String userPic;
    private String phone;
    private String email;
    @Column(name = "user_status")
    private Integer userStatus;
    private String address;



}
