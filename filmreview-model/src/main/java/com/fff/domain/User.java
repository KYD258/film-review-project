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
    private Integer userName;
    @Column(name = "real_name")
    private Integer realName;
    @Column(name = "password")
    private Integer password;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "user_pic")
    private Integer userPic;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "email")
    private Integer email;
    @Column(name = "user_status")
    private Integer userStatus;
    @Column(name = "address")
    private Integer address;
}
