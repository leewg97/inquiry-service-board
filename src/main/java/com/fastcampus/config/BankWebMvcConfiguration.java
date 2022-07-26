package com.fastcampus.config;

import com.fastcampus.security.jpa.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BankWebMvcConfiguration extends WebSecurityConfigurerAdapter { // implements WebMvcConfigurer {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 다음 경로에 대한 요청은 인증 없이 접근을 허용하도록 설정한다.
		http.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/image/**", "/webjars/**").permitAll();
		
		// 위에서 언급한 경로 외에는 모두 인증을 거치도록 설정한다.
		http.authorizeRequests().anyRequest().authenticated();
		
		// 시큐리티가 제공하는 기본 로그인 화면은 CSRF 토큰을 무조건 전달한다.
		// 하지만 사용자 정의 로그인 화면에서는 CSRF 토큰을 전달하지 않는다.
		http.csrf().disable();
		
		// 사용자가 만든 로그인 화면을 띄운다.
		http.formLogin().loginPage("/auth/login");
		
		// 로그아웃 설정
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
		
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// 인터셉터를 등록한다. 
//		registry.addInterceptor(new AuthenticateInterceptor()).addPathPatterns("/");
//	}
}
