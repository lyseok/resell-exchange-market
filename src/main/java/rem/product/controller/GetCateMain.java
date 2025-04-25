package rem.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.CateMainVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;


@WebServlet("/product/getCateMain.do")
public class GetCateMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		IProductService service = ProductServiceImpl.getInstance();
		
		List<CateMainVO> list = service.getCateMain();
		
		Gson gson = new Gson();
		
		String result = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
