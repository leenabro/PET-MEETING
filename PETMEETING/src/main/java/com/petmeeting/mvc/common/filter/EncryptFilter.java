package com.petmeeting.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.petmeeting.mvc.common.wrapper.EncryptPasswordWrapper;
@WebFilter(filterName = "encrypt", servletNames = {"login", "signup", "memberUpdatePwd"})
public class EncryptFilter implements Filter {
       
    public EncryptFilter() {
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		EncryptPasswordWrapper wrapper = new EncryptPasswordWrapper((HttpServletRequest)request);
		
		chain.doFilter(wrapper, response);
	}


}