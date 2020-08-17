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

/**
 * Servlet implementation class WMemberLoginAction
 */
@WebServlet("/member/login")
public class WMemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WMemberLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		WMemberDAO dao=WMemberDAO.getInstance();
		int flag=dao.loginCheck(userid, pwd);
		int admin=-1;
		if(flag==0 || flag==1) {
			HttpSession session=request.getSession();
			session.setAttribute("userid", userid);
			admin=flag;
			session.setAttribute("admin", admin);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		out.println(flag);
	}

}
