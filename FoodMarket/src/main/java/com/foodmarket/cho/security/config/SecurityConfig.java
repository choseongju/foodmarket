package com.foodmarket.cho.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 스프링 시큐리티 설정
@Configuration  // 컨테이너에 빈 으로 생성되어야 한다
@EnableWebSecurity  // Web Secutiry 활성화
			// 아래 스프링 시큐리티 필터가 스프링 필터 체인에 등록된다.

			// ↓ 등록할 필터 객체
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();    // CSRF 비활성화
		
		http // HttpSecurity  객체
		
			/**********************************************
			 * ① request URL 에 대한 접근 권한 세팅
		     * ExpressionInterceptUrlRegistry 객체 리턴.  이하 이 객체의 메소드체이닝
		     **********************************************/
			.authorizeRequests()
			
			 // ↓ /sample/user/** 주소로 들어오는 요청은 인증이 필요.
			.antMatchers("/user/**").authenticated()
			
			// ↓ /sample/member/** 주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_MEMBER 나 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
			.antMatchers("/owner/**").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
			
			// ↓ /sample/admin/**  주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			
			// ↓ 그 밖의 다른 요청은 모두 permit!
			.anyRequest().permitAll()
			
			.and()  // 다시, HttpSecurity 객체 리턴.  다른 세팅 전환시 중간에 호출
			
			/******************************************** 
			 * ② 폼 로그인 설정
			 * FormLoginConfigurer<HttpSecurity> 리턴.  이하 이 객체의 메소드체이닝
			 ********************************************/
			.formLogin() // form 기반 인증 페이지 활성화.  만약 .loginPage(url) 가 세팅되어 있지 않으면 '디폴트 로그인' form 페이지가 활성화 된다
			.loginPage("/login")   // 로그인 필요한 상황 발생시 매개변수의 url (로그인 폼) 로 request 발생
			
			// 로그인 처리
			.loginProcessingUrl("/loginOk")  // "/loginOk" url 로 POST request 가 들어오면 시큐리티가 낚아채서 처리, 대신 로그인을 진행해준다.
            								// 이와 같이 하면 Controller 에서 /longinOk 를 만들지 않아도 된다!
			.defaultSuccessUrl("/")  // '직접 /login' → /loginOk 에서 성공하면 "/" 로 이동시키기
		
						// 만약 다른 특정페이지에 진입하려다 로그인 하여 성공하면 해당 페이지로 이동 (너무 편리!)
			// 로그인 성공후 수행할 코드 등록
//			.successHandler(new AuthenticationSuccessHandler() {
//				
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//						Authentication authentication) throws IOException, ServletException {
//					System.out.println("## 로그인 성공 ##");
//					
//				}
//			})
			.successHandler(new CustomLoginSuccessHandler("/"))
			
			// 로그인 실패후 수행할 코드 등록
//			.failureHandler(new AuthenticationFailureHandler() {
//				
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						AuthenticationException exception) throws IOException, ServletException {
//					System.out.println("## 로그인 실패 ##");
//					
//				}
//			})
			.failureHandler(new CustomLoginFailureHandler())
			
			.and()  // 다시, HttpSecurity 객체 리턴. 세팅중 다른 세팅 전환시 호출

			/********************************************
			 * ④ 예외처리 설정
			 * ExceptionHandlingConfigurer<HttpSecurity>  리턴.  이하 이 객체의 메소드체이닝
			 ********************************************/
			.exceptionHandling()
//			.accessDeniedHandler(new AccessDeniedHandler() {
//				
//				@Override
//				public void handle(HttpServletRequest request, HttpServletResponse response,
//						AccessDeniedException accessDeniedException) throws IOException, ServletException {
//					System.out.println("## 권한 오류 ##");
//					
//				}
//			})
			.accessDeniedHandler(new CustomAccessDeniedHandler())
			
			
			.and()  // 다시, HttpSecurity 객체 리턴. 세팅중 다른 세팅 전환시 호출

			/********************************************
			 * ③ 로그아웃 설정
			 * LogoutConfigurer<HttpSecurity>  리턴.  이하 이 객체의 메소드체이닝
			 ********************************************/

			.logout()
			.logoutUrl("/logout")  // 로그아웃 수행 url
			.logoutSuccessUrl("/") // 로그아웃 성공후 redirect url
			.invalidateHttpSession(false)   // sesssion invalidate 디폴트 true
//			.deleteCookies("JSESSIONID")  // 쿠키 제거
//			.logoutSuccessHandler(new LogoutSuccessHandler() {
//				
//				@Override
//				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//						throws IOException, ServletException {
//					System.out.println("## 로그아웃 성공 ##");
//					
//				}
//			})
			.logoutSuccessHandler(new CustomLogoutSuccessHandler())
			;
	}	
}