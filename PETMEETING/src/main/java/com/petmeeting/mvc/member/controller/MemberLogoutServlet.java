

package com.petmeeting.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.admin.model.vo.Admin;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLogoutServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (Member) session.getAttribute("loginMember");
		Admin admin = (Admin) session.getAttribute("admin");
		
		if(loginMember != null || admin != null) {
			session.invalidate();
		}
		
//		if(session != null) {
//			session.invalidate();
//		}
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}