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

public class WClassDAO {
	//디비셋팅
	private static WClassDAO instance=new WClassDAO();
	public static WClassDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/wiser");
		return ds.getConnection();
	}
	
	//게시물등록
	public int classInsert(WClassDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="INSERT INTO wclass(classnum, classname, stu_num, stu_regdate, topic, content, "
					+ "clevel, uploadfile)"
					+ "VALUES(wclass_seq.nextval,?,0,0,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getClassname());
			ps.setString(2, vo.getTopic());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getClevel());
			ps.setString(5, vo.getUploadFile());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//검색 아닌 전체보기
		public ArrayList<WClassDTO> classList() {
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			ArrayList<WClassDTO>arr=new ArrayList<WClassDTO>();
			
			try {
				con=getConnection();
				String sql="select * from wclass";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					WClassDTO dto=new WClassDTO();
					dto.setClassnum(rs.getInt("classnum"));
					dto.setClassname(rs.getString("classname"));
					dto.setStu_num(rs.getInt("stu_num"));
					dto.setStu_regdate(rs.getString("stu_regdate"));
					dto.setTopic(rs.getString("topic"));
					dto.setContent(rs.getString("content"));
					dto.setClevel(rs.getString("clevel"));
					dto.setUploadFile((rs.getString("uploadfile")));
					arr.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con, st, rs);
			}
			return arr;
		}
	
		//검색 전체보기
	public ArrayList<WClassDTO> classList(int startRow, int endRow) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<WClassDTO>arr=new ArrayList<WClassDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from wclass order by classnum desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				WClassDTO dto=new WClassDTO();
				dto.setClassnum(rs.getInt("classnum"));
				dto.setClassname(rs.getString("classname"));
				dto.setStu_num(rs.getInt("stu_num"));
				dto.setStu_regdate(rs.getString("stu_regdate"));
				dto.setTopic(rs.getString("topic"));
				dto.setContent(rs.getString("content"));
				dto.setClevel(rs.getString("clevel"));
				dto.setUploadFile((rs.getString("uploadfile")));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//게시물 상세보기
	public ArrayList<WClassDTO> classList(String field, String word, int startRow, int endRow) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<WClassDTO>arr=new ArrayList<WClassDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from wclass where upper ("+field+") like upper ('%"+word+"%')");
			sb.append(" order by classnum desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				WClassDTO dto=new WClassDTO();
				dto.setClassnum(rs.getInt("classnum"));
				dto.setClassname(rs.getString("classname"));
				dto.setStu_num(rs.getInt("stu_num"));
				dto.setStu_regdate(rs.getString("stu_regdate"));
				dto.setTopic(rs.getString("topic"));
				dto.setContent(rs.getString("content"));
				dto.setClevel(rs.getString("clevel"));
				dto.setUploadFile((rs.getString("uploadfile")));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//게시물정보 수정
	public int getCount(String field, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		String sql="";
		
		try {
			con=getConnection();
			st=con.createStatement();
			if(word.equals("")) {
				sql="select count(*) from wclass";
			}else {
				sql="select count(*) from wclass where upper ("+field+") like upper ('%"+word+"%')";
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
	
	//���� �󼼺���
	public WClassDTO classView (int classnum) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		WClassDTO dto=null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select * from wclass where classnum="+classnum;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new WClassDTO();
				dto.setClassnum(rs.getInt("classnum"));
				dto.setClassname(rs.getString("classname"));
				dto.setClevel(rs.getString("clevel"));
				dto.setContent(rs.getString("content"));
				dto.setStu_num(rs.getInt("stu_num"));
				dto.setStu_regdate(rs.getString("stu_regdate"));
				dto.setTopic(rs.getString("topic"));
				dto.setUploadFile((rs.getString("uploadfile")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
	}
	
	//�������� ����
	public int classUpdate(WClassDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		try {
			con=getConnection();
			String sql="update wclass set classname=?, topic=?, content=?, clevel=?, uploadfile=? where classnum=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getClassname());
			ps.setString(2, vo.getTopic());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getClevel());
			ps.setString(5, vo.getUploadFile());
			ps.setInt(6, vo.getClassnum());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//����
	public void classDel(int classnum) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="delete from wclass where classnum='"+classnum+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//�ݱ� closeConnection
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
