package com.petmeeting.mvc.walkboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.petmeeting.mvc.common.util.FileRename;
import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;
import com.petmeeting.mvc.walkboard.model.service.WalkBoardService;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;


@WebServlet(name = "walkBoardUpdate", urlPatterns = { "/walkboard/walkupdate" })
public class WalkBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardUpdateServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	Dog dog = new Dog();
    	
    	if (loginMember != null) {
    		WalkBoard walkBoard = new WalkBoardService().getWalkBoardBywbNo(Integer.parseInt(request.getParameter("wbNo")), true);
    		
    		if (walkBoard != null && loginMember.getMCode() == walkBoard.getMemberCode()) {
    			dog = new MemberService().findDogByCode(walkBoard.getMemberCode());
    	    	
    	    	request.setAttribute("dog", dog);
    			
    			request.setAttribute("walkboard", walkBoard);
    			request.getRequestDispatcher("/views/walkboard/walkupdate.jsp").forward(request, response);
    			
    		} else {
    			request.setAttribute("msg", "잘못된 접근입니다.");
        		request.setAttribute("location", "/walkboard/walklist");
        		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    		}
    		
    	} else {
    		request.setAttribute("msg", "로그인 후 작성 해주세요.");
    		request.setAttribute("location", "/");
    		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}
    }
    
    @Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
    	
    	if (loginMember != null) {
    		
    		String path = getServletContext().getRealPath("/resources/upload/walkboard");
    		
    		int maxSize = 10485760;
    		
    		String encoding = "UTF-8";
    		
    		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    		
    		WalkBoard walkBoard = new WalkBoardService().getWalkBoardBywbNo(loginMember.getMCode(), true);
    		
    		
    		if (walkBoard != null && loginMember.getMCode() == walkBoard.getMemberCode()) {
    			walkBoard.setWbTitle(mr.getParameter("title"));
    			walkBoard.setWbContent(mr.getParameter("content"));
    			walkBoard.setWbNo(Integer.parseInt(mr.getParameter("boardNo")));
    			
    			String originalFileName = mr.getOriginalFileName("upfile");
    			String filesystemName = mr.getFilesystemName("upfile");
    			
    			if (originalFileName != null && filesystemName != null) {
    				
    				File file = new File(path + "/" + walkBoard.getRenamedFileName());
    				
    				if (file.exists()) {
    					file.delete();
    					
    				}
    				
    				walkBoard.setOriginalFileName(originalFileName);
    				walkBoard.setRenamedFileName(filesystemName);
    			}
    			
    			int result = new WalkBoardService().save(walkBoard);
    			
    			if (result > 0) {
    				request.setAttribute("msg", "게시글 수정 성공");
	    			request.setAttribute("location", "/walkboard/walkview?wbNo=" + walkBoard.getWbNo());
    				
    			} else {
    				request.setAttribute("msg", "게시글 수정 실패");
	    			request.setAttribute("location", "/walkboard/walkupdate?wbNo=" + walkBoard.getWbNo());
    				
    			}
    			
    		} else {
    				request.setAttribute("msg", "잘못된 접근입니다.");
    				request.setAttribute("location", "/walkboard/list");
    			}
    			
    		} else {
    			request.setAttribute("msg", "로그인 후 수정 해주세요.");
    			request.setAttribute("location", "/");
    			
    		}
    				
    		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }
}