package com.example.carteraiden.demotry.controller;

import com.example.carteraiden.demotry.dto.AccessTokenDTO;
import com.example.carteraiden.demotry.dto.GithubUser;
import com.example.carteraiden.demotry.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired  // 相当于实例化各变量，可以直接用
    private GithubProvider githubProvider;
    @Value("${github.client.id}")  //自动读取配置文件的值
    private String clientId;
    @Value("${github.client.secret}")
    private  String clientSecret;
    @Value("${github.redirect.uri}")
    private  String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String stats){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setStatre(stats);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUer(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}

