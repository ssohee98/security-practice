package com.example.restuserservice.security;

import com.example.restuserservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment env;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.env = env;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //권한에 따른 허용 URL 설정
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/WEB-INF/", "/META-INF/").permitAll()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())

                //login 설정
                .formLogin()
                .loginPage("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .defaultSuccessUrl("/")

                //.successForwardUrl("/")

                //logout 설정
                .and()

                .logout()
                //.logoutUrl("/logout")
                //.deleteCookies("JSESSIONID")
                //.invalidateHttpSession(true)
                .logoutSuccessUrl("/");

                //.and()
                //.exceptionHandling()
                //.accessDeniedPage("/denied");
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
