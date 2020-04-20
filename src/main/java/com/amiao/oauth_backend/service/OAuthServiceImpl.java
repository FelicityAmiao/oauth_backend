package com.amiao.oauth_backend.service;

import com.amiao.oauth_backend.entity.OAuthRequest;
import com.amiao.oauth_backend.entity.OAuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.amiao.oauth_backend.consts.OAuthConst.*;

@Service
public class OAuthServiceImpl {

    private RestTemplate restTemplate = new RestTemplate();

    public String getAccessTokenFromGithub(String code) {
        OAuthResponse response = restTemplate.postForObject(REQUEST_TOKEN_URL, buildHttpEntity(new OAuthRequest(CLIENT_ID, CLIENT_SECRET, code)), OAuthResponse.class);
        return response.getAccess_token();
    }

    private HttpEntity<Object> buildHttpEntity(OAuthRequest oAuthRequest) {
        return new HttpEntity<Object>(oAuthRequest, buildHeaders());
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }
}
