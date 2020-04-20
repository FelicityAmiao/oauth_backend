package com.amiao.oauth_backend.controller;

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
    public ResponseEntity getString(@PathVariable String code) {
        return ResponseEntity.ok(service.getAccessTokenFromGithub(code));
    }
}
