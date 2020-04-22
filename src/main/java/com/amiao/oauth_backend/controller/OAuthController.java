package com.amiao.oauth_backend.controller;

import com.amiao.oauth_backend.entity.ResponseResult;
import com.amiao.oauth_backend.entity.UserInfoResponseByToken;
import com.amiao.oauth_backend.service.OAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private OAuthServiceImpl service;

    @RequestMapping("/{code}")
    public ResponseEntity getUserInfoFromGithub(@PathVariable String code) {
        String accessTokenFromGithub = service.getAccessTokenFromGithub(code);
        System.out.println(accessTokenFromGithub);
        UserInfoResponseByToken result = getUserInfoByAccessToken(accessTokenFromGithub);
        return ResponseEntity.ok(new ResponseResult<Object>("success", result));
    }

    private UserInfoResponseByToken getUserInfoByAccessToken(String accessTokenFromGithub) {
        return service.getUserInfoFromGithub(accessTokenFromGithub);
    }
}
