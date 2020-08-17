package com.wboard.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wmember.model.CommentDTO;
import com.wmember.model.WBoardDAO;


/**
 * Servlet implementation class CommentInsertAction
 */
@WebServlet("/board/commentInsert")
public class CommentInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg=request.getParameter("msg");
		int bnum=Integer.parseInt(request.getParameter("num"));
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("userid");
//		if(userid==null) {//�α��� �ȵ�
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out=response.getWriter();
//			out.println("1");
//		}else {
			CommentDTO comment=new CommentDTO();
			comment.setBnum(bnum);
			comment.setMsg(msg);
			comment.setUserid(userid);
			WBoardDAO dao=WBoardDAO.getInstance();
			dao.commentInsert(comment);
			//commentList ArrayList�� ��� json���� ����
			response.sendRedirect("commentList?num="+bnum);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
