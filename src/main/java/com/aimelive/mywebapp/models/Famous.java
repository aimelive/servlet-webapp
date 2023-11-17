package com.aimelive.mywebapp.models;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Famous {
    private UUID id;

    private String starName;
    private String realName;

    private String bio;
    private int age;

    private Date createdDate;
}
