package com.petmeeting.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "MemberDogUpdate", urlPatterns = { "/member/dogUpdate" })
public class MemberDogUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDogUpdateServlet() {
    }
    
    @Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = ( session == null) ? null : (Member)session.getAttribute("loginMember");
    	
    	if(loginMember != null) {
    		request.getRequestDispatcher("/views/member/dogUpdate.jsp").forward(request, response);
    	} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
    	
    }

}
