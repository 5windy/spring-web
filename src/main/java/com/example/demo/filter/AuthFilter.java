package com.example.demo.filter;

import com.example.demo.user.domain.AuthUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
@WebFilter("/*")
public class AuthFilter implements Filter {

    private final List resources = List.of(
            "/script",
            "/style"
    );

    private final List urls = List.of(
            "/",
            "/index",
            "/home",
            "/header",
            "/footer",
            "/users/signin",
            "/users/signup"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if(urls.contains(uri) || resources.stream().anyMatch(o -> uri.startsWith((String) o))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 인증된 사용자 확인 (로그인 검증)
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        if(authUser == null) {
            response.sendRedirect("/users/signin");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
