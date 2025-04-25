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


@WebServlet("/main/notice/view.do")
public class MainNoticeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		int notice_no = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println("MAIN_NOTICE_VIEW => notice_no: " +notice_no);
		
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		NoticeBoardVO vo = service.selectNoticeBoard(notice_no);
		
		request.setAttribute("board", "notice");
		request.setAttribute("noticeTitle", vo.getNotice_title());
		request.setAttribute("noticeAt", vo.getNotice_at());
		request.setAttribute("noticeCont", vo.getNotice_cont());
		request.setAttribute("noticeCnt", vo.getNotice_cnt());
		
		
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainNoticeView.jsp").forward(request, response);
	}


}
