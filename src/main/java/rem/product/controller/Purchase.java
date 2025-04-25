package rem.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.MemberVO;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.CateNameVO;
import rem.product.vo.ProductVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/product/purchase.do")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		IProductService pservice = ProductServiceImpl.getInstance();
		IFileService fservice = FileServiceImpl.getInstance();
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		int mem_bal = loginInfo.getMem_bal();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", loginInfo.getMem_no());
		param.put("prod_no", prod_no);
		
		ProductVO pvo = pservice.getProductDetail(param);
		CateNameVO cvo = pservice.getCateName(prod_no);
		List<ImgFileVO> list = fservice.getProductfileImg(prod_no);
		
		request.setAttribute("pvo", pvo);
		request.setAttribute("cvo", cvo);
		request.setAttribute("list", list);
		request.setAttribute("mem_bal", mem_bal);
		
		request.getRequestDispatcher("/WEB-INF/product/purchase.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
