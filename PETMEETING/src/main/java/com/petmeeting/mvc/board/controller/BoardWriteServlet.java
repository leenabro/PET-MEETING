package com.petmeeting.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import com.petmeeting.mvc.board.model.service.BoardService;
import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.common.util.FileRename;
import com.petmeeting.mvc.member.model.vo.Member;

@WebServlet(name = "boardWrite", urlPatterns = { "/board/write" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardWriteServlet() {

    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        Member loginMember =(session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
           request.getRequestDispatcher("/views/board/write.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "로그인 후 작성해 주세요");
            request.setAttribute("location", "/");
            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
         }
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member loginMember =(session == null) ? null : (Member)session.getAttribute("loginMember");
        
        if(loginMember != null) {
            String path = getServletContext().getRealPath("/resources/upload/board");
            
            int maxSize = 104857600;
            
            String encoding = "UTF-8";

            MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());

            
            Board board = new Board();
            
            board.setBoardCode(mr.getParameter("category"));
            board.setMemberCode(loginMember.getMCode());
            board.setBoardTitle(mr.getParameter("title"));
            board.setBoardContent(mr.getParameter("content"));
            board.setSubjectId(mr.getParameter("subject"));
            
            board.setOriginalFileName(mr.getOriginalFileName("upfile"));
            board.setRenamedFileName(mr.getFilesystemName("upfile"));

            int result = new BoardService().save(board);
            
            if(result > 0) {
         	   request.setAttribute("msg", "게시글이 등록되었습니다.");
         	   request.setAttribute("location", "/board/list?boardCode="+board.getBoardCode());
            } else {
         	   request.setAttribute("msg", "게시글이 등록되지 않았습니다.");
         	   request.setAttribute("location", "/board/write");
            }
        } else {
     	   request.setAttribute("msg", "로그인 후 작성해 주세요");
     	   request.setAttribute("location", "/");
        }
        
        request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
     }

 }