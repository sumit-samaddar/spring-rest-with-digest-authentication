package com.springsec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM = "SMART_REALM";

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() {
		return userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		registry.userDetailsService(userDetailsServiceBean());
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().authenticationEntryPoint(digestEntryPoint()).and()

				.addFilter(digestAuthenticationFilter(digestEntryPoint())).csrf().disable().authorizeRequests()
				.antMatchers("/user/**").hasAuthority("GROUP_ADMIN");

	}

	public DigestAuthenticationFilter digestAuthenticationFilter(
			DigestAuthenticationEntryPoint digestAuthenticationEntryPoint) {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
		digestAuthenticationFilter.setUserDetailsService(userDetailsServiceBean());
		digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
		return digestAuthenticationFilter;
	}

	@Bean
	public DigestAuthenticationEntryPoint digestEntryPoint() {
		DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
		digestAuthenticationEntryPoint.setKey("acegi");
		digestAuthenticationEntryPoint.setRealmName(REALM);
		digestAuthenticationEntryPoint.setNonceValiditySeconds(10);
		return digestAuthenticationEntryPoint;
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
