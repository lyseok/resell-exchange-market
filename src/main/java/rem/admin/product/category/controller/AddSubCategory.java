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


@WebServlet("/admin/addSubCategory.do")
public class AddSubCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		ICategoryService service = CategoryServiceImpl.getInstence();

		// ajax로 보낸 데이터 파라미터로 넣어주기 위해 꺼냄
		Integer mainId = Integer.parseInt(request.getParameter("mainId"));
		String value = request.getParameter("value");
		System.out.println(mainId);
		System.out.println(value);
		
		// 파라미터로 보내주기 위한 vo에 세팅
		CateSubVO vo = new CateSubVO();
		vo.setCate_main_id(mainId);
		vo.setCate_sub_name(value);
		
		// 인서트 성공 여부 확인
		Integer cnt = service.insertSubCate(vo);
		System.out.println("인서트 성공 여부 확인: " + cnt);
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(cnt));
		out.flush();
	}

}
