package com.wboard.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmember.model.PageUtil;
import com.wmember.model.WBoardDAO;
import com.wmember.model.WBoardDTO;


/**
 * Servlet implementation class WBoardListAction
 */
@WebServlet("/board/boardlist")
public class WBoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WBoardListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WBoardDAO dao=WBoardDAO.getInstance();
		
		String pageNum=request.getParameter("pageNum")==null? "1":request.getParameter("pageNum");
		int currentPage=Integer.parseInt(pageNum);
		int pageSize=5;
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
				
		int count=0;
		String field=request.getParameter("field")==null?"":request.getParameter("field");
		String word=request.getParameter("word")==null?"":request.getParameter("word");
		
		//어떤 강의인지 강의 번호를 가져오기
		String classNumber=request.getParameter("classnum");
		int classnum=Integer.parseInt(classNumber);
		
		//수강후기 개수 출력
		count=dao.getCount(field, word,classnum);
		
		//총페이지 수
		int totPage=(count/pageSize)+(count%pageSize==0?0:1);
		int pageBlock=3;
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		if(endPage>totPage) endPage=totPage;
		
		PageUtil pu=new PageUtil();
		pu.setCurrentPage(currentPage);
		pu.setEndPage(endPage);
		pu.setPageBlock(pageBlock);
		pu.setStartPage(startPage);
		pu.setTotPage(totPage);
		pu.setField(field);
		pu.setWord(word);
		pu.setClassnum(classnum);
		
		ArrayList<WBoardDTO> arr=null;
		if(word.equals("")) {
			arr=dao.boardList(startRow, endRow, classnum);
		}else {
			arr=dao.boardList(field, word, startRow, endRow, classnum);
		}
		
		int rowNo=count-((currentPage-1)*pageSize); //매 페이지의 시작번호
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("pu", pu);
		request.setAttribute("board", arr);
		request.setAttribute("count", count);
		
		RequestDispatcher rd=request.getRequestDispatcher("listResult.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
