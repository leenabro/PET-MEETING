package com.petmeeting.mvc.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.petmeeting.mvc.member.model.service.MemberService;

@WebServlet("/sendAuthMail")
public class MemberSendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSendEmailServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = new HashMap<>();
		String inputEmail = request.getParameter("inputEmail");
		String authCode = new MemberService().sendEmail(inputEmail);
		
		
//		request.setAttribute("authCode", authCode);
		
		map.put("checkAuthCode", authCode);
		
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());
		
		
		
	}	
}