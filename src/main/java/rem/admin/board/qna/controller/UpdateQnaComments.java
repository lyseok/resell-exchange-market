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


@WebServlet("/admin/updateQnaComments.do")
public class UpdateQnaComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		IQnaCommentsService service = QnaCommentsServiceImpl.getInstence();
		
		QnaCommentsVO vo = new QnaCommentsVO();
		
		int num = Integer.parseInt(request.getParameter("qna_no"));
		vo.setQna_no(num);
		vo.setCmt_cont(request.getParameter("commentsText"));

		int cnt = service.updateQnaComments(vo);

		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		
		if(cnt > 0) {
			vo = service.selectQnaCmt(num);
			out.print(gson.toJson(vo));			
		}else {
			
		}
		out.flush();
		
		
	}

}
