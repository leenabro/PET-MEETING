package com.petmeeting.mvc.walkboard.model.dao;

import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petmeeting.mvc.common.util.PageInfo;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoardReply;

public class WalkBoardDao {
	
	/* 게시글 총 개수 조회 쿼리 */
	public int getWalkBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM WALKBOARD WHERE WB_STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}
	
	/* 게시글 목록 조회 쿼리 */
	public List<WalkBoard> findAll(Connection connection, PageInfo pageInfo) {
		List<WalkBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, WB_NO, WB_TITLE, M_NICKNAME, CREATE_DATE, WB_VIEWS, WB_STATUS, ORIGINAL_FILENAME "
					 + "FROM ( "
					 + "        SELECT ROWNUM AS RNUM, "
					 + "        WB_NO, "
					 + "        WB_TITLE, "
					 + "        M_NICKNAME, "
					 + "        CREATE_DATE, "
					 + "        WB_VIEWS, "
					 + "        WB_STATUS, "
					 + "        ORIGINAL_FILENAME "
					 + "        FROM ( "
					 + "        SELECT  WB.WB_NO, "
					 + "                WB.WB_TITLE, "
					 + "                M.M_NICKNAME, "
					 + "                WB.CREATE_DATE, "
					 + "                WB.WB_VIEWS, "
					 + "                WB.WB_STATUS, "
					 + "                WB.ORIGINAL_FILENAME "
					 + "        FROM WALKBOARD WB JOIN MEMBER M ON(WB.M_CODE = M.M_CODE)  "
					 + "        WHERE WB.WB_STATUS = 'Y' ORDER BY WB.WB_NO DESC "
					 + "    ) "
					 + ") "
					 + "WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				WalkBoard walkBoard = new WalkBoard();
				
				walkBoard.setWbNo(rs.getInt("WB_NO"));
				walkBoard.setRowNum(rs.getInt("RNUM"));
				walkBoard.setMemNickname(rs.getString("M_NICKNAME"));
				walkBoard.setWbTitle(rs.getString("WB_TITLE"));
				walkBoard.setCreateDate(rs.getDate("CREATE_DATE"));
				walkBoard.setWbViews(rs.getInt("WB_VIEWS"));
				walkBoard.setWbStatus(rs.getString("WB_STATUS"));
				walkBoard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				
				list.add(walkBoard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	/* 게시글 상세 조회 쿼리 */
	public WalkBoard findWalkBoardBywbNo(Connection connection, int wbNo) {
		WalkBoard walkBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT M.M_CODE, WB.WB_NO, WB.WB_TITLE, M.M_NICKNAME, WB.WB_VIEWS, WB.WB_CONTENT, WB.CREATE_DATE, WB.MODIFY_DATE, WB.ORIGINAL_FILENAME, WB.RENAMED_FILENAME "
					 + "FROM WALKBOARD WB "
					 + "JOIN MEMBER M ON(WB.M_CODE = M.M_CODE) "
					 + "WHERE WB.WB_STATUS = 'Y' AND WB.WB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, wbNo);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				walkBoard = new WalkBoard();
				
				walkBoard.setMemberCode(rs.getInt("M_CODE"));
				walkBoard.setWbNo(rs.getInt("WB_NO"));
				walkBoard.setWbTitle(rs.getString("WB_TITLE"));
				walkBoard.setMemNickname(rs.getString("M_NICKNAME"));
				walkBoard.setWbViews(rs.getInt("WB_VIEWS"));
				walkBoard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				walkBoard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				walkBoard.setWbContent(rs.getString("WB_CONTENT"));
				
				walkBoard.setReplies(this.getRepliesBywbNo(connection, wbNo));
				walkBoard.setCreateDate(rs.getDate("CREATE_DATE"));
				walkBoard.setModifyDate(rs.getDate("MODIFY_DATE"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return walkBoard;
	}
	
	/* 댓글 조회 쿼리 */
	private List<WalkBoardReply> getRepliesBywbNo(Connection connection, int wbNo) {
		List<WalkBoardReply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT M.M_CODE, R.WR_NO, R.WB_NO, R.WR_CONTENT, M.M_NICKNAME, R.CREATE_DATE, R.MODIFY_DATE "
					 + "FROM WALKREPLY R "
					 + "JOIN MEMBER M ON(R.M_CODE = M.M_CODE) "
					 + "WHERE R.WR_STATUS='Y' AND WB_NO=? "
					 + "ORDER BY R.WR_NO DESC";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, wbNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				WalkBoardReply walkBoardReply = new WalkBoardReply();
				
				walkBoardReply.setMCode(rs.getInt("M_CODE"));
				walkBoardReply.setWcNo(rs.getInt("WR_NO"));
				walkBoardReply.setWbNo(rs.getInt("WB_NO"));
				walkBoardReply.setWbComment(rs.getString("WR_CONTENT"));
				walkBoardReply.setWcNickname(rs.getString("M_NICKNAME"));
				walkBoardReply.setCreateDate(rs.getDate("CREATE_DATE"));
				walkBoardReply.setModifyDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(walkBoardReply);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replies;
	}

	/* 게시글 등록 쿼리 */
	public int insertWalkBoard(Connection connection, WalkBoard walkBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO WALKBOARD VALUES (WB_NO_SEQ.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, 0, DEFAULT, ?, ?)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, walkBoard.getMemberCode());
			pstmt.setString(2, walkBoard.getDogId());
			pstmt.setString(3, walkBoard.getWbTitle());
			pstmt.setString(4, walkBoard.getWbContent());
			pstmt.setString(5, walkBoard.getOriginalFileName());
			pstmt.setString(6, walkBoard.getRenamedFileName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/* 게시글 수정 쿼리 */
	public int updateWalkBoard(Connection connection, WalkBoard walkBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE WALKBOARD SET WB_TITLE=?, WB_CONTENT=?, ORIGINAL_FILENAME=?, RENAMED_FILENAME=?, MODIFY_DATE=SYSDATE WHERE WB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, walkBoard.getWbTitle());
			pstmt.setString(2, walkBoard.getWbContent());
			pstmt.setString(3, walkBoard.getOriginalFileName());
			pstmt.setString(4, walkBoard.getRenamedFileName());
			pstmt.setInt(5, walkBoard.getWbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 조회수 증가 쿼리 */
	public int updateWbViews(Connection connection, WalkBoard walkBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE WALKBOARD SET WB_VIEWS=? WHERE WB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			walkBoard.setWbViews(walkBoard.getWbViews() + 1);
			
			pstmt.setInt(1, walkBoard.getWbViews());
			pstmt.setInt(2, walkBoard.getWbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 댓글 등록 쿼리 */
	public int insertReply(Connection connection, WalkBoardReply walkBoardReply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO WALKREPLY VALUES (WR_NO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE, DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, walkBoardReply.getWbNo());
			pstmt.setInt(2, walkBoardReply.getMCode());
			pstmt.setString(3, walkBoardReply.getWbComment());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 게시글 삭제 쿼리
	public int updateWbStatus(Connection connection, int wbNo, String wbStatus) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE WALKBOARD SET WB_STATUS=? WHERE WB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, wbStatus);
			pstmt.setInt(2, wbNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}
	
	/* 댓글 삭제 쿼리 */
	public int updateWcStatus(Connection connection, int wcNo, String wcStatus) {
		int result = 0;
		String query = "UPDATE WALKREPLY SET WR_STATUS=? WHERE WR_NO=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(query);
		
			pstmt.setString(1, wcStatus);
			pstmt.setInt(2, wcNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
}