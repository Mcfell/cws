package com.ckc.cws.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ckc.cws.global.FinalValue;

/**
 * Servlet Filter implementation class UserLoginFilter
 */
@WebFilter("/UserLoginFilter")
public class UserLoginFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginFilter.class);
    private Set<String> ignoreUrls;
    private FilterConfig filterConfig;
	/**
     * Default constructor. 
     */
    public UserLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String userString = null;
		
		if (ignoreUrls.contains(uri)) {
			chain.doFilter(request, response);
		}else {
			userString = (String)req.getSession().getAttribute("user");
			if (userString==null) {
				LOGGER.debug("you need login first");
				rep.setContentType("application/json");
				PrintWriter out = rep.getWriter();
				out.append("{\"statue\":");
				out.append(String.valueOf(FinalValue.FAILED));
				out.append(",\"contenT\":\"you need login first\"}");
				out.flush();
				out.close();
				return;
			}
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
		if(uri.indexOf("ajax")!=-1){
			response.setContentType("application/json");
		}
	}
	
	/*
	 * 设置不过滤的界面
	 */
	private void setUnCheckPath(String unCheckPaths) {
		String[] pStrings = unCheckPaths.split(";");
		ignoreUrls = new HashSet<String>();
		for (int i = 0; i < pStrings.length; i++) {
			ignoreUrls.add(pStrings[i]);
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
		String uncheckString = filterConfig.getInitParameter("uncheckpath");
		setUnCheckPath(uncheckString);
	}

}
