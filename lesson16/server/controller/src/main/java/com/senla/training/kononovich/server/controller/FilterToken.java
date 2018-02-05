package com.senla.training.kononovich.server.controller;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.server.service.TokenUtility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		Long id = TokenUtility.getInstance().getUserId(token);
        if (id != null) {
            try {
                chain.doFilter(request, response);
            } catch (IOException e) {
                log.error(e.toString());
            } catch (ServletException e) {
                log.error(e.toString());
            }
        } else {
            rs.setStatus(401);
        }

    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
