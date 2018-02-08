package com.senla.training.kononovich.server.controller;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.senla.training.kononovich.server.api.service.ITokenUtility;
import com.senla.training.kononovich.server.api.service.IUserHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterToken implements Filter {

    private FilterConfig filterConfig;
    private static Logger log = Logger.getLogger(FilterToken.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        String token = req.getHeader("token");

        ITokenUtility tokenUtility = WebApplicationContextUtils.
                getRequiredWebApplicationContext(filterConfig.getServletContext()).
                getBean( ITokenUtility.class);

        IUserHandler userHandler=WebApplicationContextUtils.
                getRequiredWebApplicationContext(filterConfig.getServletContext()).
                getBean( IUserHandler.class);
        Long id = tokenUtility.getUserIdByToken(token);
        if (id != null) {
            try {
                userHandler.setUserId(id);
                chain.doFilter(request, response);
            } catch (Exception e) {
                log.error(e.toString());
            }
        }
        rs.setStatus(401);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
