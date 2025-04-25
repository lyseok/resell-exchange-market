package rem.admin.product.category.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.category.service.CategoryServiceImpl;
import rem.admin.product.category.service.ICategoryService;
import rem.product.vo.CateSubVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


@WebServlet("/admin/updateSubCate.do")
public class UpdateSubCate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		ICategoryService service = CategoryServiceImpl.getInstence();
		
		CateSubVO vo = new CateSubVO();
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		System.out.println(subId + " <<<< mainId 잘 넘어왔는 지 확인");
		String  subName = request.getParameter("subName");
		System.out.println(subName + " <<<< mainName 잘 넘어왔는 지 확인");
		
		
		vo.setCate_sub_id(Integer.parseInt(request.getParameter("subId")));
		vo.setCate_sub_name(request.getParameter("subName"));
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(service.updateSubCate(vo)));
		out.flush();
	}

}
