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
import rem.login.vo.MemberVO;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.ProdImgVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/product/updateProduct.do")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		String login = (String)session.getAttribute("login");
		
		System.out.println("login"+login);
		if(login == null) {
			response.sendRedirect(request.getContextPath() + "/accessCheck.do");
		} else {
			IProductService pservice = ProductServiceImpl.getInstance();
			IFileService fservice = FileServiceImpl.getInstance();
			List<ProdImgVO> list = new ArrayList<ProdImgVO>();
			ImgFileVO fvo = new ImgFileVO();
		}
		
		request.getRequestDispatcher("/WEB-INF/product/updateProduct.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
