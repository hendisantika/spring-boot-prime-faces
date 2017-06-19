package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/index.faces").permitAll()
				.antMatchers("/error-test").permitAll() //TODO remove this line
				.antMatchers("/login.faces").permitAll()
				.antMatchers("/error/**").permitAll()
				.antMatchers("/registration.faces").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login.faces").failureUrl("/login.faces?error=true")
				.defaultSuccessUrl("/admin/home.faces")
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .invalidateHttpSession(true)
				.logoutSuccessUrl("/login.faces").permitAll()
				.and()				
				.exceptionHandling().accessDeniedPage("/error/access-denied.faces");
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/javax.faces.resource/**", "/resources/i18n/**","/i18n/**", "/error/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}