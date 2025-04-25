package rem.admin.board.qna.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.qna.service.IQnaCommentsService;
import rem.admin.board.qna.service.QnaCommentsServiceImpl;
import rem.admin.board.qna.vo.QnaCommentsVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


@WebServlet("/admin/qnaCommentView.do")
public class QnaCommentView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IQnaCommentsService service = QnaCommentsServiceImpl.getInstence();
		
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		
		QnaCommentsVO vo = service.selectQnaCmt(qnaNo);
		System.out.println("QnaCommentView vo: " + vo);

		if(vo != null) {
			
		}else {
			
		}

		Gson gson = new Gson();
		
		String res = gson.toJson(vo);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
				 
	}

}
