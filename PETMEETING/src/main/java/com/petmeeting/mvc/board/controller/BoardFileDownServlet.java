package com.petmeeting.mvc.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "boardFileDown", urlPatterns = { "/board/fileDown" })
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFileDownServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oname = request.getParameter("oname");
		String rname = request.getParameter("rname");
		
		System.out.println(oname + "," + rname);
		
		String filePath = getServletContext().getRealPath("/resources/upload/board/" + rname);
		
		File downFile = new File(filePath);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		String downName = null;

		String userAgent = request.getHeader("user-agent");
		boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;
		
		if(isMSIE) {
			downName = URLEncoder.encode(oname, "UTF-8").replaceAll("\\+", "%20");
		} else {
			downName = new String(oname.getBytes("UTF-8"), "ISO-8859-1");			
		}
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + downName);
		
		
		int read = -1;
		
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}

}