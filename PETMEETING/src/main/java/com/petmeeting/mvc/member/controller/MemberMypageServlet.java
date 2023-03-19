package com.petmeeting.mvc.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "memberMypage", urlPatterns = { "/member/myPage" })
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberMypageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember");
    	List<Dog> list = null;
    	
    	if(loginMember != null) {
    		list = new MemberService().findAllDogByCode(loginMember.getMCode());
    		
    		request.setAttribute("list", list);
    		
    		request.getRequestDispatcher("/views/member/myPage.jsp").forward(request, response);
    	} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
	}

}
