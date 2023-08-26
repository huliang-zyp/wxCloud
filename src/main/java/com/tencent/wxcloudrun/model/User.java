package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;

    // Getters and setters
}

