package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.member.model.vo.Member;
import com.petmeeting.mvc.walkboard.model.service.WalkBoardService;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoardReply;

@WebServlet(name = "walkBoardReply", urlPatterns = { "/walkBoard/reply" })
public class WalkBoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardReplyServlet() {
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		int result = 0;
		
		if (loginMember != null) {
			
			int wbNo = Integer.parseInt(request.getParameter("wbNo"));
			String wbComment = request.getParameter("commentContent");
			
			WalkBoardReply walkBoardReply = new WalkBoardReply();
			
			walkBoardReply.setWbNo(wbNo);
			walkBoardReply.setMCode(loginMember.getMCode());
			walkBoardReply.setWbComment(wbComment);
//			walkBoardReply.setWcNickname(loginMember.getNickname());
			
			result = new WalkBoardService().saveReply(walkBoardReply);
			
			if (result > 0) {
				request.setAttribute("msg", "댓글 등록 성공");
				request.setAttribute("location", "/walkboard/walkview?wbNo=" + wbNo);
				
			} else {
				request.setAttribute("msg", "댓글 등록 실패");
				request.setAttribute("location", "/walkboard/walkview?wbNo=" + wbNo);
			}
			
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요");
        	request.setAttribute("location", "/");
			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}