package com.petmeeting.mvc.walkboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "walkBoardSearch", urlPatterns = { "/walkBoard/search" })
public class WalkBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WalkBoardSearchServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/walkboard/search.jsp").forward(request, response);
	}
}