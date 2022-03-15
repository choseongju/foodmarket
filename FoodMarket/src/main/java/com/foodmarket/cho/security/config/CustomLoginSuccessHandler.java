package com.foodmarket.cho.security.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//AuthenticationSuccessHandler(I)
//  └─ SavedRequestAwareAuthenticationSuccessHandler
// https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/web/authentication/SavedRequestAwareAuthenticationSuccessHandler.html

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	public CustomLoginSuccessHandler(String defaultTargetUrl) {
		// 로그인후 특별히 redirect 할 url 이 없는경우 기본적으로 rediret 할 url
		setDefaultTargetUrl(defaultTargetUrl);
	}

	// 로그인 성공후 수행할 동작
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		System.out.println("### 로그인 성공 ###");
		
		// 로그인 정보 출력
		System.out.println("접속IP: " + getClientIp(request));
		PrincipalDetails userDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println("username: " + userDetails.getUsername());
		System.out.println("password: " + userDetails.getPassword());
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		System.out.println("authorities: " + roleNames);  // 권한이름들
		
		// 로그인 시간을 세션에 저장하기
		LocalDateTime loginTime = LocalDateTime.now();
		System.out.println("로그인시간: " + loginTime);
		request.getSession().setAttribute("loginTime", loginTime);
		
		// 로그인 직전 url 로 redirect 하기...
		HttpSession session = request.getSession();
		if(session != null) {
			String redirectUrl = (String)session.getAttribute("url_prior_login");
			if(redirectUrl != null) {
				session.removeAttribute("url_prior_login");
				getRedirectStrategy().sendRedirect(request, response, redirectUrl);
			} else {
				super.onAuthenticationSuccess(request, response, authentication);		
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);			
		}		
	}
	
    // request 를 한 client ip 가져오기
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("WL-Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_CLIENT_IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getRemoteAddr();
         }
         return ip;
    }	
	
	
	
}



















