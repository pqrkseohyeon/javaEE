package com.book.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookDTO;

/**
 * Servlet implementation class BookInsertAction
 */
@WebServlet("/book/insert")
public class BookInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("inert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BookDTO dto = new BookDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setAuthor(request.getParameter("author"));
		dto.setPublisher(request.getParameter("publisher"));
		dto.setP_date(request.getParameter("p_date"));
		
		dto.setImg(request.getParameter("img"));
		dto.setInfo(request.getParameter("info"));
		response.sendRedirect("list.jsp");
		
		
		
	}

}
