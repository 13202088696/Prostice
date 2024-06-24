package com.itbaizhan.shopping_manager_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//配置类
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    //spring security配置
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //自定义表单登录
        http.formLogin(
                form ->{
                    form.usernameParameter("username") //用户名项
                            .passwordParameter("password") //密码项
                            .loginProcessingUrl("/admin/login") //登录提交路径
                            .successHandler(new MyLoginSuccessHandler()) //登录成功处理器
                            .failureHandler(new MyLoginFailureHandler()); //的路失败处理器
                }
        );

        //权限拦截
        http.authorizeHttpRequests(
                resp->{
                    resp.requestMatchers("/login","/admin/login").permitAll(); //登录不需要认证
                    resp.anyRequest().authenticated(); //其余需要认证
                }
        );

        //退出登录配置
        http.exceptionHandling(
                exception->{
                    exception.authenticationEntryPoint(new MyAuthenticationEntryPoint()) //未登录处理器
                            .accessDeniedHandler(new MyAccessDeniedHandler()); //权限不足
                }
        );

        //跨域
        http.cors();

        //关闭csrf
        http.csrf(csrf->{
            csrf.disable();
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
