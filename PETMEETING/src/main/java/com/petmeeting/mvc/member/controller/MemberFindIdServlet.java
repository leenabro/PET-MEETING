package com.petmeeting.mvc.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet("/findId")
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindIdServlet() {
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
    	
    	request.getRequestDispatcher("/views/member/findId.jsp");
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String> map = new HashMap<>();
    	String email = request.getParameter("findIdByEmail");
    	
    	map.put("ReturnFindId", new MemberService().findIdByEmail(email)); 

    	response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());

    	
    }

}