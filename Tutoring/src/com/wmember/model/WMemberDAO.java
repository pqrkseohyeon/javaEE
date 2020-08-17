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

public class WMemberDAO {
	//������
	private static WMemberDAO instance=new WMemberDAO();
	public static WMemberDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/wiser"); //context.xml�� name�� jdbc/member�� �ٲٱ�
		return ds.getConnection();
	}
	
	//ȸ������
	public void memberInsert(WMemberDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="INSERT INTO wmember(num, name, userid, pwd, email, address, "
					+ "classnum, reg_date, postcode, detailaddress, extraaddress, admin)"
					+ "VALUES(wmember_seq.nextval,?,?,?,?,?,0,sysdate,?,?,?,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getAddress());
			ps.setInt(6, vo.getPostcode());
			ps.setString(7, vo.getDetailAddress());
			ps.setString(8, vo.getExtraAddress());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	//ȸ������Ʈ ����
	public ArrayList<WMemberDTO> memberList() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<WMemberDTO>arr=new ArrayList<WMemberDTO>();
		
		try {
			con=getConnection();
			String sql="select * from wmember";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				WMemberDTO dto=new WMemberDTO();
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//ȸ������ ����
	public int memberUpdate(WMemberDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		try {
			con=getConnection();
			String sql="update wmember set name=?, pwd=?, email=?, postcode=?, address=?, detailaddress=?, extraaddress=? where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getEmail());
			ps.setInt(4, vo.getPostcode());
			ps.setString(5, vo.getAddress());
			ps.setString(6, vo.getDetailAddress());
			ps.setString(7, vo.getExtraAddress());
			ps.setString(8, vo.getUserid());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
		
	//ȸ�� �󼼺���
	public WMemberDTO memberView(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		WMemberDTO dto=null;
		try {
			con=getConnection();
			String sql="select * from wmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new WMemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				dto.setAddress(rs.getString("address"));
				dto.setPostcode(rs.getInt("postcode"));
				dto.setDetailAddress(rs.getString("detailaddress"));
				dto.setExtraAddress(rs.getString("extraaddress"));
				dto.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
	}
		
	//����
	public void memberDel(String userid) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="delete from wmember where userid='"+userid+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//���̵� �ߺ�Ȯ�� ����ϱ� ��ư
	public String idCheck(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String flag="yes"; //��밡��
		try {
			con=getConnection();
			String sql="select * from wmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no"; //���Ұ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}

	//�α���üũ (��й�ȣ ����: 2, ȸ���ƴ�: -1, ȸ��: 0, ������: 1)
	public int loginCheck(String userid, String pwd) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int flag=-1;
		try {
			con=getConnection();
			String sql="select pwd, admin from wmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //id ����
				if(rs.getString("pwd").equals(pwd)) { //��� ��ġ
					flag=rs.getInt("admin");
				}else { //��� ����
					flag=2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}
	
	//ȸ����
	public int getCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from wmember";
			st=con.createStatement();
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
