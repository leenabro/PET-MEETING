package com.petmeeting.mvc.member.controller;

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

@WebServlet(name = "memberDogInsert", urlPatterns = { "/member/dog/insert" })
public class MemberDogInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDogInsertServlet() {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession(false);
    	Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember");
    	Member member = new Member();
    	int num = 0;
    	int result = 0;
    	Dog dog = new Dog();
    	
    	if(loginMember != null) {
    		// 파일이 저장될 경로
    		String path = getServletContext().getRealPath("/resources/upload/dogimg");
    		// 파일의 최대 사이즈 지정 (10MB)
        	int maxSize = 10485760;
        	// 파일 인코딩 설정
        	String encoding = "UTF-8";
        	// DefaultFileRenamePolicy : 중복되는 이름 뒤에 1 ~ 9999 붙인다.
        	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    		
    		num = new MemberService().countMemberDog(loginMember.getMCode());
    		
    		member.setMCode(loginMember.getMCode());
    		// 파일에 대한 정보를 가져올 때
    		dog.setImgOriginName(mr.getOriginalFileName("upimg"));
    		dog.setImgReName(mr.getFilesystemName("upimg"));
    		dog.setNum(num+1);
    		// 폼 파라미터로 넘어온 값들
    		dog.setName(mr.getParameter("dogName"));
    		dog.setKind(mr.getParameter("dogKind"));
    		dog.setSize(mr.getParameter("dogSize"));
    		dog.setGender(mr.getParameter("dogGender"));
    		dog.setNeutered(mr.getParameter("neutered"));
    		String vaccine = mr.getParameter("vaccine") != null ?
    				String.join(",", mr.getParameterValues("vaccine")) : null;
    		
    		dog.setVaccine(vaccine);
    		
    		result = new MemberService().dogSave(dog, member);
    		
			if (result > 0) {
				request.setAttribute("msg", "강아지 정보 저장 성공");
				request.setAttribute("location", "/member/myPage");
			} else {
				request.setAttribute("msg", "강아지 정보 저장 실패");
				request.setAttribute("location", "/member/update"); 
			}
    	} else {
    		request.setAttribute("msg", "로그인 후 이용하세요.");
    		request.setAttribute("location", "/"); 
    	}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }

}
