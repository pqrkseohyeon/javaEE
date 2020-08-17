package com.wmember.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmember.model.WMemberDAO;
import com.wmember.model.WMemberDTO;

/**
 * Servlet implementation class WMemberInsertAction
 */
@WebServlet("/member/insert")
public class WMemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WMemberInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WMemberDAO dao=WMemberDAO.getInstance();
		WMemberDTO member=new WMemberDTO();
		member.setName(request.getParameter("name"));
		member.setUserid(request.getParameter("userid"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setPostcode(Integer.parseInt(request.getParameter("sample6_postcode")));
		member.setAddress(request.getParameter("sample6_address"));
		member.setDetailAddress(request.getParameter("sample6_detailAddress"));
		member.setExtraAddress(request.getParameter("sample6_extraAddress"));
		
		dao.memberInsert(member);
		response.sendRedirect("login");
	}

}
