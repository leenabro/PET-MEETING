package com.petmeeting.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "memberDogDelete", urlPatterns = { "/member/dogDelete" })
public class MemberDogDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDogDeleteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = ( session == null) ? null : (Member)session.getAttribute("loginMember");
		
    }

}
