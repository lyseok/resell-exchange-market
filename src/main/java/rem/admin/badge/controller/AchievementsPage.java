package rem.admin.badge.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.badge.dao.AchievementsDaoImpl;
import rem.admin.badge.service.AchievementsServiceImpl;
import rem.admin.badge.service.IAchievementsService;
import rem.admin.badge.vo.AchievementsVO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/achievementsPage.do")
public class AchievementsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		IAchievementsService service = AchievementsServiceImpl.getInstance(AchievementsDaoImpl.getInstance());
		List<AchievementsVO> list = service.selectAllAchievements();
		
		request.setAttribute("result", list);
		request.getRequestDispatcher("/WEB-INF/admin/badge/badge_list.jsp").forward(request, response);
	}

}
