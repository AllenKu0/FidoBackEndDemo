package com.example.springboot.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter ")
@Configuration
public class CorsFilter implements Filter {
    private static final String URL = "http://localhost:8080"; // URL
    private static final String OTHER_URL = "http://123.241.245.130:8080"; // OTHER URL

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestOrigin = request.getHeader("Origin");
        System.out.println("Cors : " + requestOrigin);
        if (requestOrigin.equals(URL)){
            System.out.println("Access-Control-Allow-Origin : " + requestOrigin);
            response.addHeader("Access-Control-Allow-Origin", requestOrigin);
        }
        else {
            System.out.println("Access-Control-Allow-Origin : http://123.241.245.130:8080" );
            response.addHeader("Access-Control-Allow-Origin", "http://123.241.245.130:8080");
        }
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080,http://123.241.245.130:8080/");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(servletRequest, res);
    }

    private void setAccessControlAllowOrigin(HttpServletResponse response,
                                             ServletRequest request) {
        String requestUrl = request.getRemoteAddr() + ":" + request.getRemotePort();
        System.out.println("Cors : " + requestUrl);
        if (OTHER_URL.equals(requestUrl)) {
            response.setHeader("Access-Control-Allow-Origin", OTHER_URL);
        } else {
            response.setHeader("Access-Control-Allow-Origin", URL);
        }
    }
}