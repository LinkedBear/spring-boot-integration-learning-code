package com.linkedbear.boot.security.config;

import com.linkedbear.boot.security.filter.ResourceAuthorizationFilter;
import com.linkedbear.boot.security.jwt.JwtTokenFilter;
import com.linkedbear.boot.security.jwt.JwtUtils;
import com.linkedbear.boot.security.jwt.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import java.security.PrivateKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private ResourceAuthorizationFilter resourceAuthorizationFilter;
    
    private PrivateKey privateKey;
    
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    
    private static String[] ignoreUrls = {"/login", "/logout", "/refreshToken"};
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启表单登录功能
        // http.formLogin().successHandler((request, response, authentication) -> {
        //     System.out.println(authentication);
        //     response.setContentType("application/json;charset=utf-8");
        //     response.getWriter().write("登录成功");
        // }).failureHandler((request, response, exception) -> {
        //     exception.printStackTrace();
        //     response.setContentType("application/json;charset=utf-8");
        //     response.getWriter().write("登录失败");
        // });
        // 限制所有接口都需要认证后访问
        // http.authorizeRequests().anyRequest().authenticated();
        // 限制/api开头的接口需要拥有admin角色，其余接口需要认证后访问
        // http.authorizeRequests().antMatchers("/api/**").hasRole("admin").anyRequest().authenticated();
        // 引入用户管理机制
        // http.userDetailsService(userDetailsService());
        // 关闭CSRF
        http.csrf().disable();
        // 使用cookie存放CSRF标识
        // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // 引用IOC容器中注册的UserDetailsService实现（即基于数据库管理的实现）
        http.userDetailsService(userDetailsService);
        // 开启记住我
        // http.rememberMe();
        // 持久化存储记住我的信息
        JdbcTokenRepositoryImpl remembermeRepository = new JdbcTokenRepositoryImpl();
        remembermeRepository.setJdbcTemplate(jdbcTemplate);
        http.rememberMe().tokenRepository(remembermeRepository);
        
        // 限制/api开头的接口需要拥有api权限，其余接口需要认证后访问
        // http.authorizeRequests().antMatchers("/api/**").hasAuthority("api").anyRequest().authenticated();
        // 限制/api/test接口需要拥有/api/test权限
        // http.authorizeRequests().antMatchers("/api/test").hasAuthority("/api/test").anyRequest().authenticated();
        
        // 默认的权限控制只检查是否已登录
        // http.authorizeRequests().anyRequest().authenticated();
        // 注入自定义过滤器，连接数据库动态鉴权
        http.addFilterAfter(resourceAuthorizationFilter, FilterSecurityInterceptor.class);
    
        // 开启表单登录功能，设置认证成功后响应jwt信息
        this.privateKey = RsaUtils.getPrivateKey("jwt_rsa");
        http.formLogin().successHandler((request, response, authentication) -> {
            User user = (User) authentication.getPrincipal();
            JwtUtils.writeJwtToken(response, user, privateKey, 30, 3600);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("登录成功");
        }).failureHandler((request, response, exception) -> {
            exception.printStackTrace();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("登录失败");
        });
        // 设置jwt令牌的解析过滤器执行时机比AnonymousAuthenticationFilter靠前
        http.addFilterBefore(jwtTokenFilter, AnonymousAuthenticationFilter.class);
        // 设置无状态session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 设置访问白名单
        http.authorizeRequests().antMatchers(ignoreUrls).permitAll().anyRequest().authenticated();
    }
    
    // @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userManager = new InMemoryUserDetailsManager();
        userManager.createUser(User.withUsername("xiaoshuai").password(passwordEncoder().encode("123456")).roles("admin").build());
        userManager.createUser(User.withUsername("xiaoming").password(passwordEncoder().encode("654321")).roles("user").build());
        userManager.createUser(User.withUsername("boss").password(passwordEncoder().encode("123456")).roles("admin", "manager").build());
        return userManager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
