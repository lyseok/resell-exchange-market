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

@WebServlet("/admin/insertQnaComments.do")
public class insertQnaComments extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본세팅 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		// 서비스 호출
		IQnaCommentsService service = QnaCommentsServiceImpl.getInstence();
		
		QnaCommentsVO vo = new QnaCommentsVO();
		int num = Integer.parseInt(request.getParameter("qna_no"));
		
		// 꺼낸 값 vo에 넣기 (세팅하기)
		vo.setQna_no(num);
		vo.setCmt_cont(request.getParameter("commentsText"));
		
		// insert 성공 실패값 res로 받기, service로 dao, mapper 실행하고 오기
		int cnt = service.insertQnaComments(vo);
		
		Gson gson = new Gson();
		String result = gson.toJson(cnt);
		
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

}
