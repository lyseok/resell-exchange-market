package rem.admin.product.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.view.service.IViewService;
import rem.admin.product.view.service.ViewServiceImpl;
import rem.product.vo.ViewCountVO;


@WebServlet("/admin/searchviewList.do")
public class SerchViewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IViewService service = ViewServiceImpl.getinstance();
		String select = request.getParameter("searchview");
		String value = request.getParameter("searchText");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("value", value);
		
		System.out.println("select" + select);
		System.out.println(value);
		
		List<ViewCountVO> vcvl = service.searchboardlist(map);
		
		request.setAttribute("vcvl", vcvl);
		request.getRequestDispatcher("/WEB-INF/admin/product/view/viewList.jsp").forward(request, response);
		
		
	}

}
