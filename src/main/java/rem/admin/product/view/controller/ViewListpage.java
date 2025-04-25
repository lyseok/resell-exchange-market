package rem.admin.product.view.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.view.service.IViewService;
import rem.admin.product.view.service.ViewServiceImpl;
import rem.product.vo.ViewCountVO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ViewList
 */
@WebServlet("/admin/viewList.do")
public class ViewListpage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		IViewService service = ViewServiceImpl.getinstance();
		
		List<ViewCountVO> vcvl = service.selectboardlist();
		System.out.println("서블릿에서 보내져?" + vcvl);
		
		request.setAttribute("vcvl", vcvl);
		request.getRequestDispatcher("/WEB-INF/admin/product/view/viewList.jsp").forward(request, response);
		
	}

}
