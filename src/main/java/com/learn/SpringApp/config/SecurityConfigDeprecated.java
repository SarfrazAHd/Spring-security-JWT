//package com.learn.SpringApp.config;
//
//
//import com.learn.SpringApp.controller.JwtAuthEntryPoint;
//import com.learn.SpringApp.filter.JWTFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfigDeprecated extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtAuthEntryPoint entryPoint;
//    @Autowired
//    private JWTFilter jwtFilter;
//    @Autowired
//    private UserDetailsService userDetails;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable()
//               .authorizeRequests().antMatchers("/generate/token").permitAll()
//               .anyRequest().authenticated().and()
//               .exceptionHandling().authenticationEntryPoint(entryPoint).and()
//               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//       http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//     return super.authenticationManager();
//    }
//
//    @Override
//    public void configure (AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
