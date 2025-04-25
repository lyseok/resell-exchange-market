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
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/selectAllNotification.do")
public class SelectAllNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		INotificationService service = NotificationServiceImpl.getInstance(NotificationDaoImpl.getInstance());
		List<NotificationVO> list = service.selectAllNotification(no);
		
		response.getWriter().write(new Gson().toJson(list));
	}

}
