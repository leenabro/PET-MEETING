package com.petmeeting.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "memberUpdate", urlPatterns = { "/member/update" })
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	Member member = null;
    	int result = 0;
    	
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember");
    	
    	if(loginMember != null) {
    		member = new Member();
    		
    		member.setMCode(loginMember.getMCode());
    		member.setNickname(request.getParameter("nickname"));
    		member.setPhone(request.getParameter("phone"));
    		
    		result = new MemberService().memberSave(member);
    		
    		if(result > 0) {
    			session.setAttribute("loginMember", new MemberService().findMemberById(loginMember.getId()));
    			
    			request.setAttribute("msg", "회원 정보 수정 완료");
    			request.setAttribute("location", "/member/myPage");
    		} else {
    			request.setAttribute("msg", "회원 정보 수정 실패");
    			request.setAttribute("location", "/member/myPage");
    		}
    		
    	} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			
		}
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
    }

}
