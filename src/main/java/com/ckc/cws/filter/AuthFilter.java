/*package com.ckc.cws.filter;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

	private Set<String> ignoreUrls = new HashSet<String>();

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		String url = hRequest.getRequestURI();
		String context = hRequest.getContextPath();
		url = url.replaceAll(context, "");
		for (String ign : ignoreUrls) {
			if (url.matches(ign)) {
				chain.doFilter(request, response);
				return;
			}
		}

		// 是否登录判断
		//SysUser user = (SysUser) hRequest.getSession().getAttribute(SysUser.SESSION_NAME);
//		if (null == user) {
//			hResponse.sendRedirect(context + "/login.jsp");
//			return;
//		}
		
		// 管理员
		if (user.getId() == 1) {
			chain.doFilter(request, response);
			return;
		}

		// 判断是否有权限
		for (SysResource r : user.getResources()) {//findSyResourceByUser.do
			String userUrl = r.getUrl();
			
			if(userUrl!=null&&!userUrl.equals("null")&&url!=null&&!url.equals("null")){
				
				String[] userurll=userUrl.split("/");
				String u1=userurll[userurll.length-1];
				String[] urls=url.split("/");
				String u2=urls[urls.length-1];
			
				//if (url.equals(userUrl)) {
				if (u1.equals(u2)) {
					chain.doFilter(request, response);
					return;
				}
			}
			
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("没有权限");
		out.flush();
		out.close();
	}

	public void init(FilterConfig config) throws ServletException {

		String ignoreUrl = config.getInitParameter("ignoreUrl");
		ignoreUrl = ignoreUrl.replaceAll(" ", "");
		String[] ignoreUrlArr = ignoreUrl.split(",");
		for (String ign : ignoreUrlArr) {
			ignoreUrls.add(ign);
		}

	}

	public static void main(String[] args) {

		System.out.print("ajaxLogin.do".matches("ajax(.*)\\.do"));
	}

}
*/