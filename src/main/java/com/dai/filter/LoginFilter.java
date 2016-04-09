package com.dai.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter{
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 不过滤的uri
        String[] notFilter = new String[] { "login" };
        
        // 请求的uri
        String uri = request.getRequestURI();
        
        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        
        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("user");
            if (null == obj) {
            	response.sendRedirect("/CC/login");
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
	}

}
