package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAOImpl;
import com.board.model.BoardDTO;
import com.board.model.PageUtil;



/**
 * Servlet implementation class BoardListAction
 */
@WebServlet("/board/list")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDAOImpl dao = BoardDAOImpl.getInstance();
		String pageNum=request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		String field = request.getParameter("field")==null?"":request.getParameter("field");
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		
		int currentPage  = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		
		
		int count=dao.boardCount(field,word);
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
		pu.setField(field);
		pu.setWord(word);
		
		ArrayList<BoardDTO>arr = null;
		if(word.equals("")) {
			arr=dao.boardList(startRow,endRow);
		}else {
			arr=dao.boardList(field, word, startRow, endRow);
		}
		
		int rowNo = count-((currentPage-1)*pageSize);//매 페이지의 시작번호
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("pu", pu);//페이지 저장
		request.setAttribute("board", arr);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
