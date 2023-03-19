package com.petmeeting.mvc.walkboard.model.service;

import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.commit;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.petmeeting.mvc.common.util.PageInfo;
import com.petmeeting.mvc.walkboard.model.dao.WalkBoardDao;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoardReply;

public class WalkBoardService {
	
//	게시글 총 개수 조회
	public int getWalkBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new WalkBoardDao().getWalkBoardCount(connection);
		
		close(connection);
		
		return count;
	}
	
//	게시글 목록 조회
	public List<WalkBoard> getWalkBoardList(PageInfo pageInfo) {
		List<WalkBoard> list = null;
		Connection connection = getConnection();
		
		list = new WalkBoardDao().findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}
	
//	게시글 상세 조회
	public WalkBoard getWalkBoardBywbNo(int wbNo, boolean hasRead) {
		WalkBoard walkBoard = null;
		Connection connection = getConnection();
		
		walkBoard = new WalkBoardDao().findWalkBoardBywbNo(connection, wbNo);
		
		if (walkBoard != null && !hasRead) {
			
			int result = new WalkBoardDao().updateWbViews(connection, walkBoard);
			
			if (result > 0) {
				commit(connection);
				
			} else {
				rollback(connection);
			}
		}
		
		close(connection);
		
		return walkBoard;
	}

	
//	게시글 등록
	public int save(WalkBoard walkBoard) {
		int result = 0;
		
		Connection connection = getConnection();
		
		if (walkBoard.getWbNo() > 0) {
			result = new WalkBoardDao().updateWalkBoard(connection, walkBoard);
		} else {
			result = new WalkBoardDao().insertWalkBoard(connection, walkBoard);
		}
		
		if (result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
		
	}

	public int saveReply(WalkBoardReply walkBoardReply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new WalkBoardDao().insertReply(connection, walkBoardReply);
		
		if (result > 0) {
			commit(connection);
			
		} else {
			rollback(connection);
			
		}
		
		close(connection);
		
		return result;
	}

	public int delete(int wbNo) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new WalkBoardDao().updateWbStatus(connection, wbNo, "N");
		
		if (result > 0) {
			commit(connection);
			
		} else {
			rollback(connection);
			
		}
		
		close(connection);
		
		return result;
	}
	
//	댓글 삭제
	public int deleteReply(int wbNo, int wcNo) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new WalkBoardDao().updateWcStatus(connection, wcNo, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
			
		} close(connection);
		
		System.out.println(wcNo);
		
		return result;
	}

}