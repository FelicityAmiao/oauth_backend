package com.amiao.oauth_backend.service;

import com.amiao.oauth_backend.entity.OAuthRequest;
import com.amiao.oauth_backend.entity.OAuthResponse;
import com.amiao.oauth_backend.entity.UserInfoResponseByToken;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.amiao.oauth_backend.consts.OAuthConst.*;

@Service
public class OAuthServiceImpl {

    private RestTemplate restTemplate = new RestTemplate();

    public String getAccessTokenFromGithub(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(
                new OAuthRequest(CLIENT_ID, CLIENT_SECRET, code),
                headers);

        OAuthResponse response = restTemplate.postForObject(
                REQUEST_TOKEN_URL,
                requestEntity,
                OAuthResponse.class);
        return response.getAccess_token();
    }

    public UserInfoResponseByToken getUserInfoFromGithub(String accessTokenFromGithub) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        httpHeaders.add("Authorization", "token " + accessTokenFromGithub);

        HttpEntity<Object> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<UserInfoResponseByToken> response = restTemplate.exchange(
                REQUEST_USER_INFO_BY_TOKEN_URL, HttpMethod.GET, entity, UserInfoResponseByToken.class);
        return response.getBody();
    }
}
