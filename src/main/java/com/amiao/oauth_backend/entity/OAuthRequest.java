package com.amiao.oauth_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OAuthRequest {
    private String client_id;
    private String client_secret;
    private String code;
}
