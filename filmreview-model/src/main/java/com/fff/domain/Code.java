package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Code {
    @Id
    @Column(name = "code_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeId;
    private Integer code;
    private String phone;
}
