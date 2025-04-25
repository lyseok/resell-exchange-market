package rem.notification.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.notification.dao.NotificationDaoImpl;
import rem.notification.service.INotificationService;
import rem.notification.service.NotificationServiceImpl;
import rem.notification.vo.NotificationVO;

import java.io.IOException;

@WebServlet("/viewNotification.do")
public class ViewNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int notif_id = Integer.parseInt(request.getParameter("notif_id"));
		String url = request.getParameter("url");
		
		NotificationVO vo = new NotificationVO();
		vo.setMem_no(mem_no);
		vo.setNotif_id(notif_id);
		
		INotificationService service = NotificationServiceImpl.getInstance(NotificationDaoImpl.getInstance());
		service.updateNotification(vo);
		
		response.sendRedirect(request.getContextPath() + url);

	}

}
