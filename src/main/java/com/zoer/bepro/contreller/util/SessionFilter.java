package com.zoer.bepro.contreller.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zoer on 01.02.17.
 */
public class SessionFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session= ((HttpServletRequest)request).getSession();
        if (session!=null&&!session.isNew()){
            chain.doFilter(request,response);
        }else {
            ((HttpServletResponse)response).sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
