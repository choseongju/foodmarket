package com.foodmarket.cho.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodmarket.cho.security.domain.UserDTO;
import com.foodmarket.cho.security.service.UserDetailService;

//UserDetailsService
//컨테이너에 등록한다.
//시큐리티 설정에서 loginProcessingUrl(url) 로 걸어 놓았기 때문에
//로그인시 위 url 로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는
//loadUserByUsername() 가 실행되고. 
// 인증성공하면 결과를 UserDetails 로 리턴 

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailService userService;
	
    // UserDetails 를 리턴한다,  --> 누구한테 리턴하나?
    // 시큐리티 session ( <= Authentication( <= 리턴된 UserDetails ) )	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
								        //   ↑ 로그인 시 name="username" 반드시!
								        // 아니면 usernameParameter() 값을 주어야 한다.
		
		System.out.println("loadUserByUsername(" + username + ")");
		UserDTO user = userService.findById(username);   // DB 조회
		
		// 해당 username 의 user 가 있다면
		// UserDetails 생성하여 리턴
		if(user != null) {
			PrincipalDetails userDetails = new PrincipalDetails(user);
			userDetails.setUserService(userService);
			return userDetails;
		}
		
		// 해당 username 의 user 가 없다면!
		// UsernameNotFoundException을 throw 해주도록 한다
		throw new UsernameNotFoundException(username);
	}
}


















