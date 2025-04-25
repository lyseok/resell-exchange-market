package rem.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.ProdImgVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/selectAllMainPageProd.do")
public class SelectAllMainPageProd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IProductService service = ProductServiceImpl.getInstance();
		List<ProdImgVO> list = service.selectAllMainPageProd();
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
	}

}
