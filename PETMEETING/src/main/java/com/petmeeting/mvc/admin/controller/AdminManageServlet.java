package com.petmeeting.mvc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petmeeting.mvc.admin.model.service.AdminService;
import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;

@WebServlet(name = "adminManage", urlPatterns = { "/admin/manage" })
public class AdminManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminManageServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int countBoard = 0;
    	int countMember = 0;
    	List<Board> list = null;
    	List<WalkBoard> walkList = null;
    	
    	countBoard = new AdminService().AllCountboard();
    	countMember = new AdminService().CountMember();
    	list = new AdminService().recentBoard();
    	walkList = new AdminService().recentWalkBoard();
    	
    	request.setAttribute("countBoard", countBoard);
    	request.setAttribute("countMember", countMember);
    	request.setAttribute("list", list);
    	request.setAttribute("walkList", walkList);
    	
    	
    	request.getRequestDispatcher("/views/admin/adminPage.jsp").forward(request, response);
	}

}
