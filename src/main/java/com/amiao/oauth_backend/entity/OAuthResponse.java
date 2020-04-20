package com.amiao.oauth_backend.entity;

import lombok.Data;

@Data
public class OAuthResponse {
    private String access_token;
    private String scope;
    private String token_type;
}
