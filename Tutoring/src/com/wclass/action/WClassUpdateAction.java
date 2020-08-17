package com.wclass.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.wmember.model.WClassDAO;
import com.wmember.model.WClassDTO;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/class/ClassUpdate")
public class WClassUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WClassUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WClassDAO dao=WClassDAO.getInstance();
		int uploadFileSizeLimit=5*1024*1024;
		String encType="UTF-8";
		String savePath="upload";
		ServletContext context=getServletContext();
		String uploadFilePath=context.getRealPath(savePath);
		
		//multipart/form ����� request.getParameter�� �޾ƿ� �� ����
		MultipartRequest multi=new MultipartRequest(
				request, //request��ü
				uploadFilePath, //�������� ���� ���丮
				uploadFileSizeLimit, //�ִ� ���ε� ���� ũ��
				encType, //���ڵ� ���
				new DefaultFileRenamePolicy()); //���� ���� ���̸� �ο�
		
		//���ε�� �����̸� ���ϱ�
		String fileName=multi.getFilesystemName("uploadFile");
		
		if(fileName==null) { //���� ���ε� �ȵ�
			System.out.println("���� ���ε� ���� �ʾ���");
		}
		
		WClassDTO wclass=new WClassDTO();
		wclass.setUploadFile(fileName);
		wclass.setClassname(multi.getParameter("classname"));
		wclass.setClevel(multi.getParameter("clevel"));
		wclass.setContent(multi.getParameter("content"));
		wclass.setTopic(multi.getParameter("topic"));
		int classnum=Integer.parseInt(multi.getParameter("classnum"));
		wclass.setClassnum(classnum);
		int flag=dao.classUpdate(wclass);
		if(flag==1) {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���������� �����Ǿ����ϴ�');");
			out.println("history.back(-1);");
			out.println("</script>");
		}

	}
}