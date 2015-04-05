package com.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.config.security.AuthFailureHandler;
import com.spring.config.security.AuthSuccessHandler;
import com.spring.config.security.DatabaseUserDetailsService;
import com.spring.config.security.HttpAuthenticationEntryPoint;
import com.spring.config.security.HttpLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PATH = "/login/form";

    @Autowired
    private DatabaseUserDetailsService userDetailsService;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private DataSource dataSource;
    
    
   /* @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled=true)
    static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration{
    	@Autowired
    	private PermissionEvaluator permissionEvaluator;
    	
    	protected MethodSecurityExpressionHandler expressionHandler() {
    		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
    		handler.setPermissionEvaluator(permissionEvaluator);
    		return handler;
    	}
    }*/
  
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        //authenticationProvider.setSaltSource(new ReflectionSaltSource());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        	http
        		.csrf().disable()
                .authenticationProvider(authenticationProvider())
                	.exceptionHandling()
                	.authenticationEntryPoint(authenticationEntryPoint)
                	.and()
                .authorizeRequests()
                	.antMatchers("/signup","/login","/logout","/resources/**","/").permitAll()
                	.anyRequest().authenticated()                	
                	.and();
                /*.formLogin()
                	//.loginPage("/login")
                	//.defaultSuccessUrl("index")
                	.loginProcessingUrl("user/login")
                	.usernameParameter("username")
                	.passwordParameter("password")
                	.successHandler(authSuccessHandler)
                	.failureHandler(authFailureHandler)
                	.and();*/
                /*.logout()
                	.permitAll()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/resources/index.html", "DELETE"))
                	.logoutSuccessHandler(logoutSuccessHandler)
                	.and()
                .sessionManagement()
                .maximumSessions(1);*/

        //http.authorizeRequests().anyRequest().authenticated();
    }
}