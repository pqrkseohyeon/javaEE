package com.book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BookImple {

	//DB연결
	private static BookImple instance = new BookImple();
	
	public static BookImple getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();	
	}

	
	//책 등록
	public void bookInsert(BookDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql="insert into book values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getNum());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getAuthor());
			ps.setString(4, vo.getPublisher());
			ps.setString(5, vo.getP_date());
			ps.setInt(6, vo.getPrice());
			ps.setString(7, vo.getImg());
			ps.setString(8, vo.getInfo());
			ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
		
	/*
	 * //책 전체보기 public ArrayList<BookDTO> bookList(){ Connection con = null;
	 * Statement st = null; ResultSet rs = null; ArrayList<BookDTO> arr = new
	 * ArrayList<>(); }
	 * 
	 * return arr; 
	 */
	
	
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
