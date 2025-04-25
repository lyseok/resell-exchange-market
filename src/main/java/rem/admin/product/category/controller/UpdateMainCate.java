package rem.admin.product.category.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.category.service.CategoryServiceImpl;
import rem.admin.product.category.service.ICategoryService;
import rem.product.vo.CateMainVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


@WebServlet("/admin/updateMainCate.do")
public class UpdateMainCate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		ICategoryService service = CategoryServiceImpl.getInstence();
		
		Integer mainId = Integer.parseInt(request.getParameter("mainId"));
		System.out.println(mainId + " <<<< mainId 잘 넘어왔는 지 확인");
		String  mainName = request.getParameter("mainName");
		System.out.println(mainName + " <<<< mainName 잘 넘어왔는 지 확인");
		
				
		
		CateMainVO vo = new CateMainVO();
		vo.setCate_main_id(Integer.parseInt(request.getParameter("mainId")));
		vo.setCate_main_name(request.getParameter("mainName"));
		
		
		Integer cnt = service.updateMainCate(vo);
		System.out.println("대분류 잘 업데이트 됐는 지 확인 ! " + cnt);
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(cnt));
		out.flush();
	}

}
