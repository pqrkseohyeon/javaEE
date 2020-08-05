package com.guest.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.model.GuestDAO;
import com.guest.model.GuestDTO;
import com.guest.model.PageUtil;

/**
 * Servlet implementation class GuestListAction
 */
@WebServlet("/guestbook/list.gb")
public class GuestListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GuestDAO dao = GuestDAO.getInstance();
		String pageNum=request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		
		int currentPage  = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		
		
		int count=dao.guestCount();
		//총페이지수
		int totPage =(count/pageSize)+(count%pageSize==0?0:1);
		int pageBlock=3;
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1;//1 4 7
		int endPage = startPage+pageBlock-1;//3 6
		if(endPage > totPage) endPage = totPage;
		
		
		
		PageUtil pu = new PageUtil();
		pu.setCurrentPage(currentPage);
		pu.setEndPage(endPage);
		pu.setPageBlock(pageBlock);
		pu.setStartPage(startPage);
		pu.setTotPage(totPage);
		
		ArrayList<GuestDTO>arr = dao.guestList(startRow, endRow);
		
		int rowNo = count-((currentPage-1)*pageSize);//매 페이지의 시작번호
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("pu", pu);//페이지 저장
		request.setAttribute("guestbook", arr);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("listResult.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
