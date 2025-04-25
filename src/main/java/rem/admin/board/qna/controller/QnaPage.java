package rem.admin.board.qna.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.qna.service.IQnaService;
import rem.admin.board.qna.service.QnaServiceImpl;
import rem.admin.board.qna.vo.QnaBoardVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/qnaPage.do")
public class QnaPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		request.getRequestDispatcher("/WEB-INF/admin/board/qna/qna.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		IQnaService service = QnaServiceImpl.getInstance();
		
		List<QnaBoardVO> qnalist = service.selectQnaList();		
		
		PrintWriter writer = response.getWriter();
		writer.print( new Gson().toJson(qnalist));
		writer.flush();
	}
	
	

}
