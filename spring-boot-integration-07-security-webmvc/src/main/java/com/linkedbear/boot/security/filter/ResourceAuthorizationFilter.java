package com.linkedbear.boot.security.filter;

import com.linkedbear.boot.security.dao.ResourceDao;
import com.linkedbear.boot.security.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceAuthorizationFilter implements Filter {
    
    @Autowired
    private ResourceDao resourceDao;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 在继续向后调用过滤器链之前，连接数据库判断是否当前登录人是否允许访问
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        // 首先判断当前请求是否在限制访问的列表中
        List<String> resources = resourceDao.findAll().stream().map(Resource::getPermission).collect(Collectors.toList());
        if (!resources.contains(uri)) {
            chain.doFilter(request, response);
            return;
        }
        // 在限制访问的列表中，需要鉴权
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getAuthorities().stream().anyMatch(i -> i.getAuthority().equals(uri))) {
            chain.doFilter(request, response);
            return;
        }
        throw new AccessDeniedException("权限不足");
    }
}
