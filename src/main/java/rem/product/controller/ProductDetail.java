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
import rem.product.vo.CateSubVO;
import rem.product.vo.ProdImgVO;
import rem.product.vo.ProductVO;
import rem.product.vo.ViewCountVO;
import rem.store.service.IStoreService;
import rem.store.service.StoreServiceImpl;
import rem.wishlist.dao.IWishlistDao;
import rem.wishlist.dao.WishlistDaoImpl;
import rem.wishlist.service.IWishlistService;
import rem.wishlist.service.WishlistServiceImpl;
import rem.wishlist.vo.WishlistVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/product/productDetail.do")
public class ProductDetail extends HttpServlet {
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
		
		IProductService service = ProductServiceImpl.getInstance();
		IWishlistService wservice = WishlistServiceImpl.getInstance(WishlistDaoImpl.getInstance());
		IFileService fservice = FileServiceImpl.getInstance();
		IStoreService sservice = StoreServiceImpl.getInstance();
		IMemberService mservice = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		List<ImgFileVO> list = new ArrayList<ImgFileVO>();
		ImgFileVO fvo = new ImgFileVO();
		ViewCountVO vvo = new ViewCountVO();
		ProductVO pvo2 = new ProductVO();
		
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", loginInfo.getMem_no());
		param.put("prod_no", prod_no);
		
		ProductVO pvo = service.getProductDetail(param);
		
		int updateView = service.updateProductView(prod_no);
		if(updateView >0) {
			vvo.setProd_no(prod_no);
			vvo.setMem_no(loginInfo.getMem_no());
			service.insertViewCount(vvo);
		}
		
		int countProfile = fservice.countProfileImg(pvo.getMem_no());
		
		if(countProfile==0) {
			fvo = fservice.getProfileNull();
		} else {
			
			fvo = fservice.getProfileImg(pvo.getMem_no());
		}
		
		int countWish = wservice.countProdWish(prod_no);
		int countReview = service.getCountAllReview(pvo.getMem_no());
		
		int countProduct = sservice.getCountAllProducts(pvo.getMem_no());
		
		
		MemberVO memInfo = mservice.getMemInfo(pvo.getMem_no());
		
		CateNameVO svo = service.getCateName(pvo.getProd_no());
		
		pvo2.setCate_main_id(pvo.getCate_main_id());
		pvo2.setProd_no(prod_no);
		List<ProdImgVO> clist = service.selectProdRecommend(pvo2);
		
		request.setAttribute("profileImg", fvo);
		request.setAttribute("productDetail", pvo);
		request.setAttribute("countWish", countWish);
		request.setAttribute("countReview", countReview);
		request.setAttribute("countProduct", countProduct);
		request.setAttribute("memInfo", memInfo);
		request.setAttribute("svo", svo);
		
		list = fservice.getProductfileImg(prod_no);
		
		request.setAttribute("imgfile", list);
		request.setAttribute("clist", clist);
		
		request.getRequestDispatcher("/WEB-INF/product/prodDetail.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
