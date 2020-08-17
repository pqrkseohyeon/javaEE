package com.wmember.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WBoardDAO {
	//디비셋팅
	private static WBoardDAO instance=new WBoardDAO();
	public static WBoardDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/wiser");
		return ds.getConnection();
	}
	
	//게시물등록
	public int boardInsert(WBoardDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="INSERT INTO WBoard(num, userid, subject, content, reg_date, "
					+ "readcount, classnum)"
					+ "VALUES(WBoard_seq.nextval,?,?,?,sysdate,0,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setInt(4, vo.getClassnum());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//검색 아닌 전체보기
	public ArrayList<WBoardDTO> boardList(int startRow, int endRow, int classnum) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<WBoardDTO>arr=new ArrayList<WBoardDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append(" select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from WBoard where classnum="+classnum);
			sb.append(" order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				WBoardDTO dto=new WBoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserid(rs.getString("userid"));
				dto.setClassnum(rs.getInt("classnum"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//검색 전체보기
	public ArrayList<WBoardDTO> boardList(String field, String word, int startRow, int endRow, int classnum) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<WBoardDTO>arr=new ArrayList<WBoardDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append(" select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from WBoard where classnum="+classnum);
			sb.append(" and upper ("+field+") like upper ('%"+word+"%')");
			sb.append(" order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				WBoardDTO dto=new WBoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserid(rs.getString("userid"));
				dto.setClassnum(rs.getInt("classnum"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//게시물수 출력
	public int getCount(String field, String word, int classnum) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		String sql="";
		
		try {
			con=getConnection();
			st=con.createStatement();
			if(word.equals("")) {
				sql="select count(*) from WBoard where classnum="+classnum;
			}else {
				sql="select count(*) from WBoard where upper ("+field+") like upper ('%"+word+"%') and classnum="+classnum;
			}
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	
	//게시물 상세보기
	public WBoardDTO boardView (int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		WBoardDTO dto=null;
		try {
			con=getConnection();
			st=con.createStatement();
			st.execute("update wboard set readcount = readcount+1 where num ="+num);
			String sql = "SELECT * FROM wboard WHERE num="+num;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new WBoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserid(rs.getString("userid"));
				dto.setClassnum(rs.getInt("classnum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
	}
	
	//게시물정보 수정
	public int boardUpdate(WBoardDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		try {
			con=getConnection();
			String sql="update WBoard set subject=?, content=?, reg_date=sysdate where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getSubject());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getNum());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//게시글 삭제
	public void boardDel(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="delete from WBoard where num="+num;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//댓글글 삭제
	public void commentDel(int cnum) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="delete from WComment_Board where cnum="+cnum;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//코멘트 추가 commentInsert
	public void commentInsert(CommentDTO comment) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="insert into wcomment_board(cnum, userid, msg, reg_date, bnum) "
					+ "values(wcomment_board_seq.nextval,?,?,sysdate,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getMsg());
			ps.setInt(3, comment.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}
	
	//코멘트 리스트 commentList
	public ArrayList<CommentDTO> commentList(int bnum){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<CommentDTO> arr=new ArrayList<CommentDTO>();
		try {
			con=getConnection();
			String sql="select * from wcomment_board where bnum="+bnum+ "order by cnum desc";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				CommentDTO comment=new CommentDTO();
				comment.setBnum(rs.getInt("bnum"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setReg_date(rs.getString("reg_date"));
				comment.setUserid(rs.getString("userid"));
				arr.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//닫기 closeConnection
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
