package rem.admin.product.category.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.category.service.CategoryServiceImpl;
import rem.admin.product.category.service.ICategoryService;
import rem.product.vo.CateMainVO;
import rem.product.vo.CateSubVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;


@WebServlet("/admin/subCategoryList.do")
public class SubCategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		ICategoryService service = CategoryServiceImpl.getInstence();
		
		List<CateSubVO> vo = service.selectsubCate(Integer.parseInt(request.getParameter("mainId")));
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(vo));
		out.flush();
	}

}
