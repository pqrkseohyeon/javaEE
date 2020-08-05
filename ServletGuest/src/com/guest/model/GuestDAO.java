package com.guest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class GuestDAO {
	
	//디비셋팅
private static GuestDAO instance = new GuestDAO();
	
	public static GuestDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/guest");
		return ds.getConnection();	
	}
	
	//회원수
	public int guestCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from guestbook";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {//한개만 있을때 while 여러개 있을때
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return count;
	}
	
	//추가
	public void guestInsert(GuestDTO guest) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql ="insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate,?)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getContent());
			ps.setString(3, guest.getGrade());
			ps.setString(4, guest.getIpaddr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
	}
	//전체보기 페이징
	public ArrayList<GuestDTO>guestList(int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<GuestDTO> arr = new ArrayList<>();
		
		try {
			con=getConnection();	
					
			StringBuilder sb = new StringBuilder();
			sb.append("select*from");
			sb.append(" (select aa.*,rownum rn from");
			sb.append(" (select*from guestbook order by num desc)aa");
			sb.append(" where rownum<=?) where rn>=?");
			
			
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				GuestDTO gu = new GuestDTO();
				gu.setNum(rs.getInt("num"));
				gu.setName(rs.getString("name"));
				gu.setContent(rs.getString("content"));
				gu.setGrade(rs.getString("grade"));
				gu.setCreated(rs.getString("created"));
				gu.setIpaddr(rs.getString("ipaddr"));
				arr.add(gu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps,rs);
		}
		return arr;				
	}
	
	//전체보기
	public ArrayList<GuestDTO>guestList(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql ="select*from guestbook";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				GuestDTO gu = new GuestDTO();
				gu.setNum(rs.getInt("num"));
				gu.setName(rs.getString("name"));
				gu.setContent(rs.getString("content"));
				gu.setGrade(rs.getString("grade"));
				gu.setCreated(rs.getString("created"));
				gu.setIpaddr(rs.getString("ipaddr"));
				arr.add(gu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return arr;
				
	}
	//상세보기
	public GuestDTO guestView(String name) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuestDTO dto = null;
		
		try {
			con=getConnection();
			String sql ="select*from guestbook where name='"+name+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto = new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setGrade(rs.getString("grade"));
				dto.setCreated(rs.getString("created"));
				dto.setIpaddr(rs.getString("ipaddr"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return dto;
	}
	
	
	
	//닫기
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
					if(rs!=null) rs.close();
					if(st!=null) st.close();
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

}
