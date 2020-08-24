package dev.hulk.leave.config;

import dev.hulk.leave.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LeaveSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailService myUserDetailService;

    @Autowired
    public void setMyUserDetailService(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
//                .antMatchers("/hr/**")
//                .hasRole("HR")
//                .antMatchers("/manager/**")
//                .hasAnyRole("MANAGER")
//                .antMatchers("/employee/**")
//                .hasRole("EMPLOYEE")
                .antMatchers("/**")
                .permitAll();
        http.
                formLogin()
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .permitAll();

        http
                .authorizeRequests()
                .and()
                .rememberMe()
                .tokenValiditySeconds(60);
    }
}
