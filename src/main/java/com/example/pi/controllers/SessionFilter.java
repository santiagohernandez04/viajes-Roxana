package com.example.pi.controllers;
import com.example.pi.repositories.VendedorRepositoryImp;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        String requestURI=httpServletRequest.getRequestURI();
        boolean isLogged=LoginBean.logged;
        if (isLogged || requestURI.endsWith("/login.faces")){
            chain.doFilter(request,response);
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.faces");
        }
    }
}
