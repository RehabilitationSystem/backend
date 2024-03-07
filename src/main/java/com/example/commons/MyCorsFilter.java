package com.example.commons;
 
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
 
@Component
public class MyCorsFilter implements Filter {
 
  public void doFilter(ServletRequest req, ServletResponse res,
                       FilterChain chain) throws IOException, ServletException {
  
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
    chain.doFilter(req, res);
    
  }
  
  public void init(FilterConfig filterConfig) {}
  public void destroy() {}
}