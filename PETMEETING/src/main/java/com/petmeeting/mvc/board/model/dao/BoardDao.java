package com.petmeeting.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.petmeeting.mvc.board.model.vo.Board;
import com.petmeeting.mvc.board.model.vo.Reply;
import com.petmeeting.mvc.common.util.PageInfo;
import com.petmeeting.mvc.walkboard.model.vo.WalkBoard;

import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;;

public class BoardDao {

	public int getBoardCount(Connection connection, String boardCode) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String query = "SELECT COUNT(*) FROM BOARD WHERE BOARD_STATUS='Y'";
		String query = "SELECT COUNT(*) FROM BOARD WHERE B_STATUS='Y' AND B_CODE=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			

			pstmt.setString(1, boardCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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

	public List<Board> findAll(Connection connection, PageInfo pageInfo, String boardCode ) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT RNUM, B_NO, B_CODE, BC_NAME, B_NUMBER, B_TITLE, M_CODE, M_ID, M_NICKNAME, CREATE_DATE, B_VIEWS, ORIGINAL_FILENAME "
				+ "FROM("
				+ "    SELECT ROWNUM AS RNUM, B_NO, B_CODE, BC_NAME, B_NUMBER, B_TITLE, M_CODE, M_ID, M_NICKNAME, CREATE_DATE, B_VIEWS, ORIGINAL_FILENAME "
				+ "    FROM ( "
				+ "        SELECT B.B_NO, B.B_CODE, BC.BC_NAME, B.B_NUMBER, B.B_TITLE, M.M_CODE, M.M_ID, M.M_NICKNAME, B.CREATE_DATE, B.B_VIEWS, B.ORIGINAL_FILENAME "
				+ "        FROM BOARD B "
				+ "        RIGHT OUTER JOIN MEMBER M ON(B.M_CODE = M.M_CODE) "
				+ "        RIGHT OUTER JOIN BOARD_CODE BC ON(B.B_CODE = BC.BC_CODE) "
				+ "        WHERE B_STATUS='Y' AND B.B_CODE=? "
				+ "        ORDER BY B.B_NO DESC "
				+ "    ) "
				+ ") "
				+ "WHERE RNUM BETWEEN ? AND ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, boardCode);
			
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setRowNum(rs.getInt("RNUM"));
				board.setBoardNo(rs.getInt("B_NO"));
				board.setBoardCode(rs.getString("B_CODE"));
				board.setBoardName(rs.getString("BC_NAME"));
				board.setBoardNumber(rs.getInt("B_NUMBER"));
				board.setBoardTitle(rs.getString("B_TITLE"));
				board.setMemberCode(rs.getInt("M_CODE"));
				board.setMemberId(rs.getString("M_ID"));
				board.setMemberNickName(rs.getString("M_NICKNAME"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setViews(rs.getInt("B_VIEWS"));
				
				list.add(board);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public Board findBoardByBoardNo(Connection connection, int boardNo) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B_NO, B.B_CODE, BC.BC_NAME,  B_NUMBER, CREATE_DATE, B.M_CODE, M_ID, M.M_NICKNAME, S_ID , ORIGINAL_FILENAME, RENAMED_FILENAME,B_TITLE, B_CONTENT, B_VIEWS "
				+ "FROM BOARD B "
				+ "JOIN MEMBER M ON(B.M_CODE = M.M_CODE) "
				+ "JOIN BOARD_CODE BC ON(B.B_CODE = BC.BC_CODE) "
				+ "WHERE B.B_STATUS = 'Y' AND B_NO=? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("B_NO"));
				board.setBoardCode(rs.getString("B_CODE"));
				board.setBoardName(rs.getString("BC_NAME"));
				board.setBoardNumber(rs.getInt("B_NUMBER"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setMemberCode(rs.getInt("M_CODE"));
				board.setMemberId(rs.getString("M_ID"));
				board.setMemberNickName(rs.getString("M_NICKNAME"));
				board.setSubjectId(rs.getString("S_ID"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setBoardTitle(rs.getString("B_TITLE"));
				board.setBoardContent(rs.getString("B_CONTENT"));
				
				board.setReplies(this.getRepliesByBoardNo(connection, boardNo));
				board.setViews(rs.getInt("B_VIEWS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query1 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_INTRODUCE_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		String query2 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_REVIEW_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		String query3 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_INFO_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		String query4 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_QNA_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		String query5 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_REPORT_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		String query6 = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, ?, SEQ_NOTICE_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, ?)";
		
		
		try {
			if(board.getBoardCode().equals("B1")) {
				pstmt = connection.prepareStatement(query1);
			} else if(board.getBoardCode().equals("B2")) {
				pstmt = connection.prepareStatement(query2);
			} else if(board.getBoardCode().equals("B3")) {
				pstmt = connection.prepareStatement(query3);
			} else if(board.getBoardCode().equals("B4")) {
				pstmt = connection.prepareStatement(query4);
			} else if(board.getBoardCode().equals("B5")) {
				pstmt = connection.prepareStatement(query5);
			} else if(board.getBoardCode().equals("B6")) {
				pstmt = connection.prepareStatement(query6);
			}

			pstmt.setString(1, board.getBoardCode());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setInt(3, board.getMemberCode());
			pstmt.setString(4, board.getBoardContent());
			pstmt.setString(5, board.getSubjectId());
			pstmt.setString(6, board.getOriginalFileName());
			pstmt.setString(7, board.getRenamedFileName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	

	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query="UPDATE BOARD SET B_TITLE=?,B_CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?, MODIFY_DATE=SYSDATE WHERE B_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Reply> getRepliesByBoardNo(Connection connection, int boardNo) {
		List<Reply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT R.R_NO, R.B_NO, R.R_CONTENT, M.M_ID, M.M_NICKNAME, R.CREATE_DATE, R.MODIFY_DATE "
				+ "FROM REPLY R "
				+ "JOIN MEMBER M ON(R.M_CODE = M.M_CODE) "
				+ "WHERE R.R_STATUS='Y' AND B_NO=? "
				+ "ORDER BY R.R_NO";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setReplyNo(rs.getInt("R_NO"));
				reply.setBoardNo(rs.getInt("B_NO"));
				reply.setContent(rs.getString("R_CONTENT"));
				reply.setMemberId(rs.getString("M_ID"));
				reply.setMemberNickName(rs.getString("M_NICKNAME"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setModifyDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(reply);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return replies;
	}

	public int insertReply(Connection connection, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getMemberCode());
			pstmt.setString(3, reply.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyStatus(Connection connection, int replyNo, String replyStatus) {
		int result = 0;
		String query = "UPDATE REPLY SET R_STATUS=? WHERE R_NO=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, replyStatus);
			pstmt.setInt(2, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReadCount(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET B_VIEWS=? WHERE B_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			board.setViews(board.getViews() + 1);
			
			pstmt.setInt(1, board.getViews());
			pstmt.setInt(2, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int boardStatus(Connection connection, int boardNo, String boardCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET B_STATUS='N' WHERE B_NO=? AND B_CODE=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, boardCode);			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getAllBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) "
					 + "FROM ( "
				 	 + "    SELECT B_NO "
					 + "    FROM BOARD "
					 + "     "
					 + "    UNION ALL "
					 + "     "
					 + "    SELECT WB_NO "
					 + "    FROM WALKBOARD "
					 + "    )";		
		
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

	public int getCountMember(Connection connection) {
		int count = 0;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) "
					 + "FROM MEMBER";
		
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

	public List<Board> recentBoard(Connection connection) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT RNUM, B_NO, B_TITLE, B_CONTENT, M_NICKNAME "
					 + "FROM ( "
					 + "        SELECT ROWNUM AS RNUM, B_NO, B_TITLE, B_CONTENT, M_NICKNAME "
					 + "        FROM ( "
					 + "                SELECT B_NO, B_TITLE, B_CONTENT, M_NICKNAME "
					 + "                FROM BOARD B "
					 + "                JOIN MEMBER M ON (B.M_CODE = M.M_CODE) "
					 + "                ORDER BY B_NO DESC) "
					 + "    ) "
					 + "WHERE RNUM BETWEEN 1 AND 3";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();	
				
				board.setRowNum(rs.getInt(1));
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setBoardContent(rs.getString(4));
				board.setMemberNickName(rs.getString(5));
				
				list.add(board);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<WalkBoard> recentWalkBoard(Connection connection) {
		List<WalkBoard> walkList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, WB_NO, WB_TITLE, WB_CONTENT, WB_STATUS, M_NICKNAME "
					 + "FROM ( "
					 + "        SELECT ROWNUM AS RNUM, WB_NO, WB_TITLE, WB_CONTENT, WB_STATUS, M_NICKNAME "
					 + "        FROM ( "
					 + "                SELECT WB_NO, WB_TITLE, WB_CONTENT, WB_STATUS, M_NICKNAME "
					 + "                FROM WALKBOARD W "
					 + "                JOIN MEMBER M ON (W.M_CODE = M.M_CODE) "
					 + "                ORDER BY WB_NO DESC) "
					 + "    ) "
					 + "WHERE (RNUM BETWEEN 1 AND 3) AND WB_STATUS = 'Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WalkBoard walkBoard = new WalkBoard();
				
				walkBoard.setRowNum(rs.getInt(1));
				walkBoard.setWbNo(rs.getInt(2));
				walkBoard.setWbTitle(rs.getString(3));
				walkBoard.setWbContent(rs.getString(4));
				walkBoard.setMemNickname(rs.getString(6));
				
				walkList.add(walkBoard);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return walkList;
	}
	


}