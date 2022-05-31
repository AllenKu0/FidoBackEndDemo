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
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        setAccessControlAllowOrigin(response,req);
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080,http://123.241.245.130:8080/");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");



        chain.doFilter(req, res);
    }
    private void setAccessControlAllowOrigin(HttpServletResponse response,
                                             ServletRequest request) {
        String requestUrl = request.getRemoteAddr() + ":" + request.getRemotePort();
        System.out.println("Cors : "+requestUrl);
        if (URL.equals(requestUrl)) {
            response.setHeader("Access-Control-Allow-Origin", URL);
        } else if (OTHER_URL.equals(requestUrl)) {
            response.setHeader("Access-Control-Allow-Origin", OTHER_URL);
        }
    }
}