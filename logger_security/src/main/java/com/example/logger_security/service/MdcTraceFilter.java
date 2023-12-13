package com.example.logger_security.service;


import com.example.logger_security.utils.MdcUtil;
import com.example.logger_security.utils.ThreadMdcUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MdcTraceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tid = request.getHeader("tid");
        ThreadMdcUtil.setTraceId(tid);
        // 响应头设置tid
        if (servletResponse instanceof HttpServletResponse) {
            ((HttpServletResponse) servletResponse).addHeader("tid", MdcUtil.getTraceId());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MdcUtil.removeTraceId();
    }
}
