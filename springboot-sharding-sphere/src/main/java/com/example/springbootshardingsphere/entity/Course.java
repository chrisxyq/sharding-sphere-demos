package com.example.springbootshardingsphere.entity;

import lombok.Data;

@Data
public class Course {
    private Long   cid;
    private String cname;
    private Long   userId;
    private String   cstatus;
}
