package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_creation")
public class UserCreation {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "creation_id")
    private Integer creationId;
}
