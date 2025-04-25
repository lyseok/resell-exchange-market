package rem.admin.product.category.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.category.service.CategoryServiceImpl;
import rem.admin.product.category.service.ICategoryService;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


@WebServlet("/admin/addMainCategory.do")
public class AddMainCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		ICategoryService service = CategoryServiceImpl.getInstence();
		
		
		Integer cnt = service.insertMainCate(request.getParameter("value"));
		System.out.println(cnt);
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(cnt));
		out.flush();
	}

}
