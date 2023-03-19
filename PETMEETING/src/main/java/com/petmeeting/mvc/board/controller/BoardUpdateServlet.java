package com.petmeeting.mvc.board.controller;

import java.io.File;
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

@WebServlet(name = "boardUpdate", urlPatterns = { "/board/update" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardUpdateServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        Member loginMember =(session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
        	Board board = new BoardService().getBoardByBoardNo(Integer.parseInt(request.getParameter("boardNo")), true);
        	
        	if(board != null && loginMember.getId().equals(board.getMemberId())) {
        		request.setAttribute("board", board);
        		request.getRequestDispatcher("/views/board/update.jsp").forward(request, response);
        		
        	} else {
        		request.setAttribute("msg", "잘못된 접근입니다.");
        		request.setAttribute("location", "board/list");
        		request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
        	}

        } else {
            request.setAttribute("msg", "로그인 후 수정해 주세요");
            request.setAttribute("location", "/");
            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
         }

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
        Member loginMember =(session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
        	String path = getServletContext().getRealPath("/resources/upload/board");
        	
        	int maxSize = 10485760;
        	
        	String encoding = "UTF-8";
        	
        	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
        	
        	Board board = new BoardService().getBoardByBoardNo(Integer.parseInt(mr.getParameter("boardNo")), true);
        	
        	if(board != null && loginMember.getId().equals(board.getMemberId())) {
        	
        		board.setBoardName(mr.getParameter("category"));
        		board.setBoardTitle(mr.getParameter("title"));
        		board.setBoardContent(mr.getParameter("content"));
        		board.setSubjectId(mr.getParameter("subject"));
        		
        		String originalFileName = mr.getOriginalFileName("upfile");
        		String filesystemName = mr.getFilesystemName("upfile");
        		
        		if(originalFileName != null && filesystemName != null) {
        			File file = new File(path + "/" + board.getRenamedFileName());
        			
        			if(file.exists()) {
        				file.delete();
        			}		
        			       			
        			board.setOriginalFileName(originalFileName);
        			board.setRenamedFileName(filesystemName);
        		}
        		
        		int result = new BoardService().save(board);
        		
        		if(result > 0) {
        			request.setAttribute("msg", "게시글이 수정되었습니다.");
        			request.setAttribute("location", "/board/view?boardNo="+board.getBoardNo());
        		} else {
        			request.setAttribute("msg", "게시글이 수정되지않았습니다.");
        			request.setAttribute("location", "/board/update?boardNo="+board.getBoardNo());
        		}
        		
        	} else {
        		request.setAttribute("msg", "잘못된 접근입니다.");
        		request.setAttribute("location", "board/list");
        		request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
        	}

        } else {
        	request.setAttribute("msg", "로그인 후 수정해 주세요.");
            request.setAttribute("location", "/");
        }
        request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
	
	}

}