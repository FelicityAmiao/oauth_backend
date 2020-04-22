package com.amiao.oauth_backend.entity;

import lombok.Data;

@Data
public class UserInfoResponseByToken {
    private String login;
    private String avatar_url;
    private String html_url;
}
