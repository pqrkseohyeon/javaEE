 package com.wboard.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmember.model.WBoardDAO;
import com.wmember.model.WBoardDTO;


/**
 * Servlet implementation class BoardUpdateAction
 */
@WebServlet("/board/boardUpdate")
public class WBoardUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WBoardUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("updateForm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WBoardDTO board=new WBoardDTO();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setContent(request.getParameter("content"));
		board.setSubject(request.getParameter("subject"));
		board.setUserid(request.getParameter("userid"));

		int num=Integer.parseInt(request.getParameter("num"));
		WBoardDAO dao=WBoardDAO.getInstance();
		dao.boardUpdate(board);
		response.sendRedirect("/Tutoring/board/boardDetail?num="+num);
	}

}
