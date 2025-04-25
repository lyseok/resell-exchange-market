package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.notice.dao.NoticeBoardDaoImpl;
import rem.admin.board.notice.service.INoticeBoardService;
import rem.admin.board.notice.service.NoticeBoardServiceImpl;
import rem.admin.board.notice.vo.NoticeBoardVO;
import rem.login.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/main/noticeList.do")
public class MainNoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		request.setCharacterEncoding("utf-8");
		String access = request.getParameter("access");
		
		if(access.equals("DENIED")) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}

		
		
		INoticeBoardService service
			= NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		List<NoticeBoardVO> list = service.selectAllNoticeBoard();

		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
