package com.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SAddressDAO {
	private static SAddressDAO instance = new SAddressDAO();
	
	public static SAddressDAO getInstance() {
		return instance;
	}
	
	//디비연결
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/member");
		
		return ds.getConnection();
	}
	
	//추가
	public void insertAddress(SAddressDTO ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into address values(address_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	//전체보기
	public ArrayList<SAddressDTO>addressList(){
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<SAddressDTO>arr = new ArrayList<>();	
		try {
			con=getConnection();
			String sql="select*from address";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				SAddressDTO ad = new SAddressDTO();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getLong("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("Zipcode"));
				arr.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return arr;	
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
