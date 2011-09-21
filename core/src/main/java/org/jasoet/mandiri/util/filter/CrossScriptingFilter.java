package org.jasoet.mandiri.util.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class CrossScriptingFilter implements Filter {
/*------------------------------ Fields ------------------------------*/

    private FilterConfig filterConfig;
/* --------------------- Interface Filter --------------------- */

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
    }

    public void destroy() {
        this.filterConfig = null;
    }
}

