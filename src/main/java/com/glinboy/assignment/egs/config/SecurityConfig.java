package com.glinboy.assignment.egs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.glinboy.assignment.egs.security.CustomUserDetailsService;
import com.glinboy.assignment.egs.security.JwtAuthenticationEntryPoint;
import com.glinboy.assignment.egs.security.JwtAuthenticationFilter;
import com.glinboy.assignment.egs.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;

	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	
	private final JwtTokenProvider tokenProvider;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
			JwtAuthenticationEntryPoint unauthorizedHandler,
			JwtTokenProvider tokenProvider) {
		this.customUserDetailsService = customUserDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(tokenProvider, customUserDetailsService);
	}

	private final String[] whitelist = { "/api/auth/**", "/h2-console/**", "/v3/api-docs/**", "/swagger-ui/**" };

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.headers().frameOptions().disable();
		http.cors()
			.and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(whitelist).permitAll()
			.antMatchers(HttpMethod.GET, "/api/categories/**").authenticated()
			.antMatchers("/api/categories/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/products/**").authenticated()
			.antMatchers("/api/products/**").hasRole("ADMIN")
			.anyRequest().authenticated();

		// Add our custom JWT security filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		// @formatter:on

	}
}