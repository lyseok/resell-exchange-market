package rem.admin.main.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.main.dao.AdminMainDaoImpl;
import rem.admin.main.service.AdminMainServiceImpl;
import rem.admin.main.service.IAdminMainService;
import rem.admin.main.vo.AdminTransVO;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;

@WebServlet("/admin/selectAdminMain.do")
public class SelectAdminMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IAdminMainService service = AdminMainServiceImpl.getInstance(AdminMainDaoImpl.getInstace());
		
		List<MemberVO> memberList = service.selectAdminMainMember();
		List<ProductVO> prodList = service.selectAdminMainProduct();
		List<AdminTransVO> transList = service.selectAdminMainTransact();
		System.out.println("admin main page .do");
		for(AdminTransVO vo : transList) {
			System.out.println(vo.getBuyer());
		}
		request.setAttribute("memList", memberList);
		request.setAttribute("prodList", prodList);
		request.setAttribute("transList", transList);
		
		request.getRequestDispatcher("/WEB-INF/admin/admin_main.jsp").forward(request, response);
	}

}
