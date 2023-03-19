package com.petmeeting.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petmeeting.mvc.board.model.service.BoardService;

@WebServlet(name = "boardDelete", urlPatterns = { "/board/delete" })
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		// 로그인 체크 & 본인 게시글 여부 확인 (직접 적용시켜 보세요.)
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCode = request.getParameter("boardCode");
		
		result =  new BoardService().delete(boardNo, boardCode);
		
		if(result > 0) {
			request.setAttribute("msg", "게시글 삭제 성공");
			request.setAttribute("location", "/board/list?boardCode=" + boardCode);
		} else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.setAttribute("location", "/board/view?boardNo=" + boardNo);
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}