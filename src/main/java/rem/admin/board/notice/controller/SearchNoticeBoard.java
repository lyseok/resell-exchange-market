package rem.admin.board.notice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.notice.dao.NoticeBoardDaoImpl;
import rem.admin.board.notice.service.INoticeBoardService;
import rem.admin.board.notice.service.NoticeBoardServiceImpl;
import rem.admin.board.notice.vo.NoticeBoardVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

@WebServlet("/admin/searchNoticeBoard.do")
public class SearchNoticeBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("searchNotice");
		String value = request.getParameter("searchText");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("value", value);
		
		
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		
		List<NoticeBoardVO> list = service.searchNoticeBoard(map);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
