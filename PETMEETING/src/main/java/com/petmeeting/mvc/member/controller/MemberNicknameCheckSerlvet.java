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

@WebServlet("/member/nickCheck")
public class MemberNicknameCheckSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberNicknameCheckSerlvet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Boolean> map = new HashMap<>();
		String nickname = request.getParameter("inputNickname");	
		
		map.put("duplicateNickname", new MemberService().isDuplicateNickname(nickname));
				
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());
	}

}