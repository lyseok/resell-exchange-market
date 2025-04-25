package rem.admin.board.qna.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.qna.service.IQnaService;
import rem.admin.board.qna.service.QnaServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/qnaDelete.do")
public class QnaDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 IQnaService service = QnaServiceImpl.getInstance();
		 System.out.println(service);
		 int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));

		 System.out.println(qnaNo + "QnaDelete 서블릿 qnaNO 값입니다.");
		 
		 int res = service.deleteQna(qnaNo);
		 System.out.println(res +"QnaDelete 서블릿 res 값입니다.");
		 
		 PrintWriter out = response.getWriter();
		 
		 out.print(res);
		 out.flush();
		 
		 //response.sendRedirect("/WEB-INF/admin/board/qna/qna.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
