package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petmeeting.mvc.walkboard.model.service.WalkBoardService;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;
import com.petmeeting.mvc.member.model.service.MemberService;
import com.petmeeting.mvc.member.model.vo.Member;
import com.petmeeting.mvc.common.util.FileRename;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "walkBoardWrite", urlPatterns = { "/walkBoard/walkwrite" })
public class WalkBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardWriteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
    	if (loginMember != null) {
    		Dog dog = new Dog();
    		dog = new MemberService().findDogByCode(loginMember.getMCode());
    		
    		request.setAttribute("dogName", dog.getName());
    		request.setAttribute("dogKind", dog.getKind());
    		request.setAttribute("dogSize", dog.getSize());
    		request.setAttribute("dogGender", dog.getGender());
    		request.setAttribute("dogNeutered", dog.getNeutered());
    		request.setAttribute("dogVaccine", dog.getVaccine());
    		
    		request.getRequestDispatcher("/views/walkboard/walkwrite.jsp").forward(request, response);
    		
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
    		
    		WalkBoard walkboard = new WalkBoard();
    		
    		Dog dog = new Dog();
    		
    		dog = new MemberService().findDogByCode(loginMember.getMCode());
    		
    		walkboard.setMemberCode(loginMember.getMCode());
    		walkboard.setDogId(dog.getId());
    		
    		walkboard.setWbTitle(mr.getParameter("content_title"));
    		walkboard.setWbContent(mr.getParameter("content"));
    		
    		walkboard.setRenamedFileName(mr.getFilesystemName("upfile"));
    		walkboard.setOriginalFileName(mr.getOriginalFileName("upfile"));
    		
    		
    		int result = new WalkBoardService().save(walkboard);
    		
    		
    		
    		if (result > 0) {
    			request.setAttribute("msg", "게시글 등록 성공");
        		request.setAttribute("location", "/walkboard/walklist");
        		
    		} else {
    			request.setAttribute("msg", "게시글 등록 실패");
        		request.setAttribute("location", "/walkboard/walklist");
    		}
    		
    	} else {
    		request.setAttribute("msg", "로그인 후 작성 해주세요.");
    		request.setAttribute("location", "/");

    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
	}
}