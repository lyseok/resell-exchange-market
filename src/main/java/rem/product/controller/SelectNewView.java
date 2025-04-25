package rem.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.NewViewVO;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/selectNewView.do")
public class SelectNewView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		List<NewViewVO> list = null;
//		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null) {
			MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
			int mem_no = vo.getMem_no();
			
			IProductService service = ProductServiceImpl.getInstance();
			list = service.selectNewView(mem_no);
		}
		response.getWriter().write(new Gson().toJson(list));
	}

}
