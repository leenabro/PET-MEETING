package com.petmeeting.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.board.model.service.BoardService;
import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.board.model.vo.Reply;
import com.petmeeting.mvc.member.model.vo.Member;


@WebServlet(name = "boardReply", urlPatterns = { "/board/reply" })
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardReplyServlet() {

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		//로그인체크
		
		HttpSession session = request.getSession(false);
        Member loginMember =(session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
        	Board board = new Board();
        	
        	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        	board = new BoardService().getBoardByBoardNo(boardNo, false);
        	String content = request.getParameter("content");
        	
        	
        	Reply reply = new Reply();
        	
        	reply.setBoardNo(boardNo);
        	reply.setContent(content);
        	reply.setMemberCode(loginMember.getMCode());
//		reply.setMemberNickName(loginMember.getNickname());
        	
        	result = new BoardService().saveReply(reply);
        	
        	
        	
        	if(result > 0) {
        		request.setAttribute("msg", "댓글이 등록되었습니다.");
        		request.setAttribute("location", "/board/view?boardCode="+ board.getBoardCode() +"&boardNo=" + boardNo);
//        		request.setAttribute("location", "/board/view?boardNo=" + boardNo);
        	} else {
        		request.setAttribute("msg", "댓글이 등록되지 않았습니다.");
        		request.setAttribute("location", "/board/view?boardNo=" + boardNo);
        	}
        	
        	
        } else {
        	request.setAttribute("msg", "로그인 후 작성해 주세요");
        	request.setAttribute("location", "/");
        }
        request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}