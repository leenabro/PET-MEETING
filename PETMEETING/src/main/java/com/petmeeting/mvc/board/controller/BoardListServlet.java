package com.petmeeting.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petmeeting.mvc.board.model.service.BoardService;
import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.common.util.PageInfo;


@WebServlet(name = "boardList", urlPatterns = { "/board/list" })
public class BoardListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    public BoardListServlet() {

    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int page = 0;
      int listCount = 0;

      PageInfo pageInfo = null;
      List<Board> list = null;
      String boardCode = null;

      boardCode = request.getParameter("boardCode");
         
      try {
    	  page = Integer.parseInt(request.getParameter("page"));			
      } catch (NumberFormatException e) {
    	  page = 1;
      }

      listCount = new BoardService().getBoardCount(boardCode);
      pageInfo = new PageInfo(page, 5, listCount, 10);
      list = new BoardService().getBoardList(pageInfo, boardCode);
      
      
      request.setAttribute("pageInfo", pageInfo);
      request.setAttribute("list", list);
      request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
   }

}