package com.wboard.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmember.model.WBoardDAO;
import com.wmember.model.WBoardDTO;


/**
 * Servlet implementation class BoardInsertAction
 */
@WebServlet("/board/boardinsert")
public class WBoardInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WBoardInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("courseDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WBoardDTO board=new WBoardDTO();
		board.setClassnum(Integer.parseInt(request.getParameter("classnum")));
		board.setUserid(request.getParameter("userid"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		WBoardDAO dao=WBoardDAO.getInstance();
		
		int classNumber=Integer.parseInt(request.getParameter("classnum"));
		int flag=0;
		flag=dao.boardInsert(board);

		if(flag==1){
			response.sendRedirect("/Tutoring/class/CourseDetail?num="+classNumber);
		}
	}
}