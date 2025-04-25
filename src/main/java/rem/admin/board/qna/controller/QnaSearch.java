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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


@WebServlet("/admin/qnaSearch.do")
public class QnaSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IQnaService service = QnaServiceImpl.getInstance();
		
		String value = request.getParameter("searchText");
		String select = request.getParameter("searchSelect");
		
		// 파라미터로 넣어줄 값
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("value", value);
		
		
		// 결과값 저장할 list 
		List<QnaBoardVO> list = service.searchQna(map);
		
		Gson gson = new Gson();
		String res = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
