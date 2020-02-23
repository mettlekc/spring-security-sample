package com.cheol.spring.security.sample.config;

import com.cheol.spring.security.sample.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleOauth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {

    @Autowired
    private SocialService socialService;

    public GoogleOauth2ClientAuthenticationProcessingFilter(SocialService socialService) {
        super("/login/google");
        this.socialService = socialService;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final OAuth2AccessToken accessToken = restTemplate.getAccessToken(); // 토큰 정보 가져옴
        final OAuth2Authentication auth = (OAuth2Authentication) authResult;
        final Object details = auth.getUserAuthentication().getDetails(); // 소셜에서 넘겨 받은 정보를 details에 저장
//        final FacebookUserDetails userDetails = mapper.convertValue(details, FacebookUserDetails.class); // Object mapper를 이용해서 객체 변환
//        userDetails.setAccessToken(accessToken); // access token 정보도 저장
//        final UserConnection userConnection = UserConnection.valueOf(userDetails); // UserConnection를 userDetails 기반으로 생성
//        final UsernamePasswordAuthenticationToken authenticationToken = socialService.doAuthentication(userConnection); // SocialService를 이용해서 인증 절차 진행
//        super.successfulAuthentication(request, response, chain, authenticationToken);
    }

}
