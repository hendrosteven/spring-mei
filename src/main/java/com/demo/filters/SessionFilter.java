package com.demo.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Session;
import com.demo.repository.SessionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SessionFilter implements Filter {

    @Autowired
    private SessionRepo sessionRepo;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        ObjectMapper mapper = new ObjectMapper();

        String requestPath = req.getRequestURI();
        if (requestPath.startsWith("/api/modules")) {
            String sessionId = req.getHeader("SESSIONID");
            if (sessionId != null) {
                Session session = sessionRepo.findBySessionId(sessionId);
                if (session != null) {
                    if(session.isActive()){
                        chain.doFilter(request,response);
                    }else{
                        Map<String, Object> errors = new HashMap<>();
                        errors.put("status", false);
                        errors.put("messages", new String[] { "SESSIONID is expired" });
                        errors.put("payload", null);
                        res.setStatus(HttpStatus.FORBIDDEN.value());
                        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        mapper.writeValue(res.getWriter(), errors);
                    }
                } else {
                    Map<String, Object> errors = new HashMap<>();
                    errors.put("status", false);
                    errors.put("messages", new String[] { "Please login first" });
                    errors.put("payload", null);
                    res.setStatus(HttpStatus.FORBIDDEN.value());
                    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    mapper.writeValue(res.getWriter(), errors);
                }
            } else {
                Map<String, Object> errors = new HashMap<>();
                errors.put("status", false);
                errors.put("messages", new String[] { "Please provide a SESSIONID header" });
                errors.put("payload", null);
                res.setStatus(HttpStatus.FORBIDDEN.value());
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(res.getWriter(), errors);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

}
