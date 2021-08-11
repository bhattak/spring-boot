package com.example.demo.filter;

import com.example.demo.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        log.info("Starting a transaction for req : {}", req.getRequestURI());
//        log.info("Starting a transaction for req : {}", req.getMethod());
//        if(req.getHeader("Auth").equals("Check")){
//            chain.doFilter(request, response);
//        }else{
//            throw new BadRequestException("Filtered the request");
//        }
//        log.warn("Committing a transaction for req : {}", req.getRequestURI());


        boolean valid = false; // check for something
        if (!valid) {
            ((HttpServletResponse) response).setStatus(422);
            response.getOutputStream().write("Validation error".getBytes());
            return;
        }
        chain.doFilter(request, response);

    }


}
