package rem.admin.board.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.product.dao.AdminProductDaoImpl;
import rem.admin.board.product.service.AdminProductServiceImpl;
import rem.admin.board.product.service.IAdminProductService;
import rem.product.dao.ProductDaoImpl;
import rem.product.service.IProductService;
import rem.product.vo.ProductVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

@WebServlet("/admin/adminProdSearch.do")
public class AdminProdSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IAdminProductService service = AdminProductServiceImpl.getInstence();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", request.getParameter("select"));
		map.put("value", request.getParameter("value"));
		
		List<ProductVO> list = service.searchAdminProduct(map);
		
		request.setAttribute("Searchlist", list);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
		
	}

}
