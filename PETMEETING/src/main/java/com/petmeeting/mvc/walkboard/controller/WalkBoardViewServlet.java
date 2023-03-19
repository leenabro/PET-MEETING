package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;
import com.petmeeting.mvc.walkboard.model.service.WalkBoardService;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;

@WebServlet(name = "walkBoardView", urlPatterns = { "/walkboard/walkview" })
public class WalkBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardViewServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	WalkBoard walkboard = new WalkBoard();
    	int wbNo = Integer.parseInt(request.getParameter("wbNo"));
    	Dog dog = new Dog();
    	
    	Cookie[] cookies = request.getCookies();
    	String boardHistory = "";
    	boolean hasRead = false;
    	
    	if (cookies != null) {
    		
    		for (Cookie cookie : cookies) {
    			
    			if (cookie.getName().equals("boardHistory")) {
    				boardHistory = cookie.getValue();
    				
    				if (boardHistory.contains("|" + wbNo + "|")) {
    					hasRead = true;
    					
    					break;
    				}
    			}
    		}
    	}
    	
    	if (!hasRead) {
    		Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + wbNo + "|");
    		
    		cookie.setMaxAge(-1);
    		response.addCookie(cookie);
    		
    	}
    	
    	walkboard = new WalkBoardService().getWalkBoardBywbNo(wbNo, hasRead);

    	dog = new MemberService().findDogByCode(walkboard.getMemberCode());
    	
    	request.setAttribute("dog", dog);
    	request.setAttribute("loginMember", loginMember);
    	request.setAttribute("walkboard", walkboard);
    	
    	request.getRequestDispatcher("/views/walkboard/walkview.jsp").forward(request, response);
    	
	}
}