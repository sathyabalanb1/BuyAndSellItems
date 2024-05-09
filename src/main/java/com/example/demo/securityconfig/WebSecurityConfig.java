package com.example.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("USER")
				.build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	*/
	@Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/authentication/**","/login","/customer/**","/images/**","/css/**","/javascriptfiles/**","/registerform").permitAll()
	            .requestMatchers("/insertcustomer", "/loginform", "/registerform").permitAll()
	            .requestMatchers("/customer/**","/makeorder","/displayorderplacementform").hasAuthority("Customer")
	            .requestMatchers("/dealerfrontpage","/createproductform","/dealer/**","/makeorder","/orderdetails").hasAuthority("Dealer")
	            .requestMatchers("/logout").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/loginform")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/success") // Update the default success URL
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/loginform")
	            .permitAll()
	        );
	    return http.build();
	}
	*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/authentication/**","/login","/customer/**","/images/**","/css/**","/javascriptfiles/**","/registerform","/displayforgotpasswordform","/forgotpasswordprocess","/forgot_password","/resetpassword/**").permitAll()
	            .requestMatchers("/insertcustomer", "/loginform", "/registerform","/forgotpasswordprocess").permitAll()
	            .requestMatchers("/customer/**","/makeorder","/displayorderplacementform").hasAuthority("Customer")
	            .requestMatchers("/dealerfrontpage","/createproductform","/dealer/**","/makeorder","/orderdetails").hasAuthority("Dealer")
	            .requestMatchers("/logout").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/loginform")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/success") // Update the default success URL
	            .permitAll()
	        )
	        .oauth2Login()
	        .loginPage("/loginform")
	        .defaultSuccessUrl("/oauth2LoginSuccess")
	        .and()
	        .logout(logout -> logout
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/loginform")
	            .permitAll()
	        );
	    return http.build();
	}

}
