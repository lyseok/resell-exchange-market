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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/searchAchoevements.do")
public class SearchAchoevements extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("searchAchoevements");
		String value = request.getParameter("searchText");
		System.out.println("select : " + select);
		System.out.println("value : " + value);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("value", value);
		
		IAchievementsService service = AchievementsServiceImpl.getInstance(AchievementsDaoImpl.getInstance());
		List<AchievementsVO> list = service.searchAchievements(map);
		System.out.println("list : " + list);
		
		request.setAttribute("result", list);
		request.getRequestDispatcher("/WEB-INF/admin/badge/badge_list.jsp").forward(request, response);
	}

}
