package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	private UserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth
//			.userDetailsService(userDetailsService)
//			.passwordEncoder(bCryptPasswordEncoder);
//	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

//	@Bean
//	public UserDetailsService userDetailsServiceBean() {
//		return new UserDetailsServiceImpl(userRepository);
//	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						authz -> authz
								.requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/javax.faces" +
										".resource/**", "/resources/i18n/**", "/i18n/**", "/error/**").permitAll()
								.requestMatchers("/").permitAll()
								.requestMatchers("/index.faces").permitAll()
								.requestMatchers("/error-test").permitAll() //TODO remove this line
								.requestMatchers("/login.faces").permitAll()
								.requestMatchers("/error/**").permitAll()
								.requestMatchers("/registration.faces").permitAll()
								.requestMatchers("/admin/**").hasAuthority("ADMIN")
								.anyRequest().authenticated()
				)
				.formLogin(formLogin -> formLogin
						.loginPage("/login.faces") //enable this to go to your own custom login page
						.defaultSuccessUrl("/admin/home.faces", true)
						.failureUrl("/login.faces?error=true")
				)
				.logout(logout -> logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.logoutSuccessUrl("/login?logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .invalidateHttpSession(true)
				.logoutSuccessUrl("/login.faces").permitAll()
//				.exceptionHandling().accessDeniedPage("/error/access-denied.faces")
				);
		return http.build();
	}

//	@Bean
//	public SecurityFilterChain configure(WebSecurity web) {
//		web
//				.ignoring()
//				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/javax.faces" +
//						".resource/**", "/resources/i18n/**", "/i18n/**", "/error/**");
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
