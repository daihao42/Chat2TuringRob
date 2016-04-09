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
		// �����˵�uri
        String[] notFilter = new String[] { "login" };
        
        // �����uri
        String uri = request.getRequestURI();
        
        // �Ƿ����
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // ���uri�а��������˵�uri���򲻽��й���
                doFilter = false;
                break;
            }
        }
        
        if (doFilter) {
            // ִ�й���
            // ��session�л�ȡ��¼��ʵ��
            Object obj = request.getSession().getAttribute("user");
            if (null == obj) {
            	response.sendRedirect("/CC/login");
            } else {
                // ���session�д��ڵ�¼��ʵ�壬�����
                filterChain.doFilter(request, response);
            }
        } else {
            // �����ִ�й��ˣ������
            filterChain.doFilter(request, response);
        }
	}

}
