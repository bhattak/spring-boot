package com.example.demo.filter;

import com.example.demo.exception.FilterException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MethodFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!req.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        } else {
            log.error("POST Method NOT supported !!!");
            ((HttpServletResponse) response).setStatus(422);
            response.getOutputStream().write("Validation error".getBytes());
            return;
//            throw  new FilterException("only POST is allowed  ");
        }
    }
}
