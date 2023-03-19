package com.petmeeting.mvc.admin.model.service;

import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.petmeeting.mvc.admin.model.vo.Admin;
import com.petmeeting.mvc.board.model.dao.BoardDao;
import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.member.model.dao.MemberDao;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;

public class AdminService {

	public Admin login(String id, String password) {
		Admin admin = this.findAdminById(id);
		
		if(admin == null || !admin.getPassword().equals(password)) {
			
			return null;
		}
		
		return admin;
	}

	private Admin findAdminById(String id) {
		Connection connection = getConnection();
		
		Admin admin = new MemberDao().findAdminById(connection, id);
		
		close(connection);
		
		return admin;
		
	}

	public int AllCountboard() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new BoardDao().getAllBoardCount(connection);
		
		close(connection);
		
		return count;
		
	}

	public int CountMember() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new BoardDao().getCountMember(connection);
	
		close(connection);
		
		return count;
	}

	public List<Board> recentBoard() {
		Connection connection = getConnection();
		List<Board> list = new BoardDao().recentBoard(connection);
		
		close(connection);
		
		return list;
	}

	public List<WalkBoard> recentWalkBoard() {
		Connection connection = getConnection();
		List<WalkBoard> walkList = new BoardDao().recentWalkBoard(connection);
		
		close(connection);
		
		return walkList;
	}
	
	

}
