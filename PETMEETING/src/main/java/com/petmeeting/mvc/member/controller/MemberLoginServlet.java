package com.petmeeting.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.admin.model.service.AdminService;
import com.petmeeting.mvc.admin.model.vo.Admin;
import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "login", urlPatterns = { "/member/login" })
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLoginServlet() {
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
    	if(loginMember != null) {	
    		request.setAttribute("msg", "잘못된 요청입니다. 다시 확인해 주세요.");
    		request.setAttribute("location", "/");			
    		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	} else {
    		request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String id = request.getParameter("inputId");
		String password = request.getParameter("inputPwd");
		
		Member loginMember = new MemberService().login(id, password);
		Admin admin = new AdminService().login(id, password);
		
		if (loginMember != null || admin != null) {
			
			session = request.getSession();
			
			session.setAttribute("admin", admin);
			session.setAttribute("loginMember", loginMember);
			
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			request.setAttribute("location", "/member/login");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		
		
		
	}

}