package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petmeeting.mvc.common.util.PageInfo;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;
import com.petmeeting.mvc.walkboard.model.service.WalkBoardService;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;

@WebServlet(name = "walkBoardList", urlPatterns = { "/walkboard/walklist" })
public class WalkBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardListServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	List<WalkBoard> list = null;
    	
    	try {
    		page = Integer.parseInt(request.getParameter("page"));
			
		} catch (NumberFormatException e) {
			page = 1;
		} 
    	
    	listCount = new WalkBoardService().getWalkBoardCount();
    	pageInfo = new PageInfo(page, 10, listCount, 10);
    	list = new WalkBoardService().getWalkBoardList(pageInfo);
    	
    	// System.out.println(list);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);
    	request.getRequestDispatcher("/views/walkboard/walklist.jsp").forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Dog dog = new Dog();
    	Member member = new Member();
    	
    	dog.setSize(request.getParameter("size"));
    	dog.setGender(request.getParameter("dogGender"));
    	dog.setKind(request.getParameter("kind"));
    	
    	String vaccine = request.getParameter("vaccine") != null ?
				String.join(",", request.getParameterValues("vaccine")) : null;
		
		dog.setVaccine(vaccine);
		dog.setNeutered(request.getParameter("neuter"));
		
		member.setGender(request.getParameter("gender"));
		
		// 조회 서비스로 넘기기
		
    }
    
    
}