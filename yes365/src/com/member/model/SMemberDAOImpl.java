package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SMemberDAOImpl implements MemberDAO{
	
	//DB연결
	private static SMemberDAOImpl instance = new SMemberDAOImpl();
	
	public static SMemberDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();	
	}

	
	
	//등록(회원가입)
	@Override
	public void memberInsert(MemberDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql ="insert into book_member(num, userid,name,pwd,email,phone,zipcode,addr,detailaddr,extraaddr,admin) "
					+ "values(book_member_seq.nextval,?,?,?,?,?,?,?,?,?,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			ps.setInt(6, vo.getZipcode());
			ps.setString(7, vo.getAddr());
			ps.setString(8, vo.getDetailAddr());
			ps.setString(9, vo.getExtraAddr());	
			ps.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
		
		
	}
	//회원전체보기
	@Override
	public ArrayList<MemberDTO> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql ="select*from book_member";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddr(rs.getString("addr"));
				dto.setZipcode(rs.getInt("zipcode"));
				dto.setDetailAddr(rs.getString("detailAddr"));
				dto.setExtraAddr(rs.getString("extraAddr"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		
		return arr;
	}
	
	//수정하기
	@Override
	public int memberUpdate(MemberDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="update book_member set name=?,email=?,phone=?,zipcode=?,addr=?,detailAddr=?,extraAddr=? where userid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getZipcode());
			ps.setString(5, vo.getAddr());
			ps.setString(6, vo.getDetailAddr());
			ps.setString(7, vo.getExtraAddr());
			ps.setString(8, vo.getUserid());
			flag=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//회원상세보기
	@Override
	public MemberDTO memberView(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		
		try {
			con=getConnection();
			String sql = "select*from book_member where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new MemberDTO();
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				dto.setZipcode(rs.getInt("zipcode"));
				dto.setAddr(rs.getString("addr"));
				dto.setDetailAddr(rs.getString("detailAddr"));
				dto.setExtraAddr(rs.getString("extraAddr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return dto;
	}

	//삭제
	@Override
	public void memberDel(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from book_member where userid='"+userid+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,null);
		}
		
	}
	
	//아이디 중복 체크
	@Override
	public String idCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag="yes";//사용가능
		
		try {
			con=getConnection();
			String sql="select*from book_member where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no";//사용불가능
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}		
		return flag;
	}
	
	//로그인체크
	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs =  null;
		int flag=-1; //-1:회원아님, 0:일반회원,1:관리자,2:비번오류
		
		try {
			con=getConnection();
			String sql="select*from book_member where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {//회원은 맞음
				if(rs.getString("pwd").equals(pwd)) {//비번일치
					flag=rs.getInt("admin");//adming:1 ->  관리자, 0 -> 일반회원				
				}else {
					flag=2;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return flag;
		
		
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
