package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "walkBoardMain", urlPatterns = { "/walkboard/main" })
public class WalkBoardMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardMainServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/views/walkboard/main.jsp").forward(request, response);
    }

}
