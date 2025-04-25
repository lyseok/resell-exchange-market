package rem.admin.board.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.product.service.AdminProductServiceImpl;
import rem.admin.board.product.service.IAdminProductService;
import rem.product.vo.ProductVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/prodListPage.do")
public class ProdListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/board/product/prodList.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IAdminProductService service = AdminProductServiceImpl.getInstence();
		
		List<ProductVO> list = service.selectAdminProductList();
		System.out.println("서블릿 확인 :" + list);
		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
