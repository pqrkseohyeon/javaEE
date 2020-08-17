package com.wmember.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wmember.model.WMemberDAO;
import com.wmember.model.WMemberDTO;


/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/member/update")
public class WMemberUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WMemberUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WMemberDTO member=new WMemberDTO();
		member.setName(request.getParameter("name"));
		member.setUserid(request.getParameter("userid"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setPostcode(Integer.parseInt(request.getParameter("sample6_postcode")));
		member.setAddress(request.getParameter("sample6_address"));
		member.setDetailAddress(request.getParameter("sample6_detailAddress"));
		member.setExtraAddress(request.getParameter("sample6_extraAddress"));
		
		WMemberDAO dao=WMemberDAO.getInstance();
		int flag=dao.memberUpdate(member);
		HttpSession session=request.getSession();
		if(flag==1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보가 수정되었습니다');");
			out.println("history.back(-1);");
			out.println("</script>");
		}

	}
}