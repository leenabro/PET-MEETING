package com.petmeeting.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter{

	public EncodingFilter() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String requestMethod = ((HttpServletRequest)request).getMethod();
		
		if (requestMethod.equals("POST")) {
			request.setCharacterEncoding("UTF-8");
			
		}
		
		// 다음 필터를 호출하거나, 마지막 필터면 servlet, JSP를 호출한다.
		chain.doFilter(request, response);
		
//		response.setContentType("text/html;charset=UTF8");
		
	}
	
	@Override
	public void destroy() {
	}
}