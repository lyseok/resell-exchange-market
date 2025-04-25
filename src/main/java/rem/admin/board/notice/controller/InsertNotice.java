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
import rem.notification.dao.NotificationDaoImpl;
import rem.notification.service.INotificationService;
import rem.notification.service.NotificationServiceImpl;
import rem.notification.vo.NotificationVO;

import java.io.IOException;

import com.google.gson.Gson;

@WebServlet("/admin/insertNotice.do")
public class InsertNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("noticeTitle");
		String content = request.getParameter("noticeContent");
		// String file = request.getParameter("noticeFile");
		
		NoticeBoardVO vo = new NoticeBoardVO();
		
		vo.setNotice_title(title);
		vo.setNotice_cont(content);
		
		INoticeBoardService service = NoticeBoardServiceImpl.getInstance(NoticeBoardDaoImpl.getInstance());
		int res = service.insertNoticeBoard(vo);
		
		if(res > 0) {
			NotificationVO notifiVo = new NotificationVO();
			notifiVo.setNotif_type(2);
			notifiVo.setNotif_message("새로운 공지사항이 등록되었습니다.");
			notifiVo.setNotif_url("/main/notice/view.do?noticeNo=");
			INotificationService notifiservice = NotificationServiceImpl.getInstance(NotificationDaoImpl.getInstance());
			if(notifiservice.insertNotification(notifiVo) > 0) {
				response.getWriter().write(new Gson().toJson(res));			
			}
			
		}
	}

}
