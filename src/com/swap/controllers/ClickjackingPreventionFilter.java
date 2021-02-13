package com.swap.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/")
public class ClickjackingPreventionFilter implements Filter {

	private String mode = "DENY";
	private String mode1 = "sameorigin";
    public ClickjackingPreventionFilter() {
    }


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
        res.addHeader("X-FRAME-OPTIONS", mode ); 
        res.addHeader("X-FRAME-OPTIONS", mode1);
        res.addHeader("X-XSS-PROTECTION", "1; mode=block");
        res.addHeader("X-CONTENT-TYPE-OPTIONS", "nosniff");
		/* res.addHeader("CONTENT-SECURITY-POLICY", "default-src 'self';"); */
		
		  res.addHeader("STRICT-TRANSPORT-SECURITY",
		  "max-age=31536000;includeSubDomains;perload");
		 
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		String configMode = fConfig.getInitParameter("mode");
        if ( configMode != null ) {
            mode = configMode;
        }
	}

}
