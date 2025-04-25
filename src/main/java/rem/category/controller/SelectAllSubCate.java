package rem.category.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.category.dao.CategoryDaoImpl;
import rem.category.service.CategoryServiceImpl;
import rem.category.service.ICategoryService;
import rem.category.vo.CategoryMainVO;
import rem.category.vo.CategorySubVO;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/selectAllSubCate.do")
public class SelectAllSubCate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		ICategoryService service = CategoryServiceImpl.getInstance(CategoryDaoImpl.getInstance());
		List<CategorySubVO> list = service.selectAllSubCate(no);
		
		response.getWriter().write(new Gson().toJson(list));
	}

}
