package rem.store.controller;

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
import rem.store.service.IStoreService;
import rem.store.service.StoreServiceImpl;

import java.io.IOException;



@WebServlet("/store/storePage.do")
public class StorePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		String param = request.getParameter("param"); 
		int value =	Integer.parseInt(request.getParameter("value"));
		
		IStoreService service = StoreServiceImpl.getInstance();
		IFileService fservice = FileServiceImpl.getInstance();
		
		MemberVO storeVO = new MemberVO();
		if(param.equals("mem_no"))
			storeVO = service.getStoreInfoByMem(value);
		else if(param.equals("prod_no"))
			storeVO = service.getStoreInfoByProd(value);
		else {
			System.out.println("■■■■■■■파라미터 \"param\" 값이 할당된 접근이 필요합니다.");
			response.sendRedirect(request.getContextPath() + "/mainPage.do");
		}
		
		int storeId = storeVO.getMem_no();

		int countAllProducts     = service.getCountAllProducts(storeId);
		int countSoldoutProducts = service.getCountSoldoutProducts(storeId);
		int countProfileImg = fservice.countProfileImg(storeId);
		double storeRatingAvg		 = service.storeRatingAvg(storeId);
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■리뷰평점 ==+> " +storeRatingAvg);
		
		
		ImgFileVO profileImg = new ImgFileVO();
		if(countProfileImg==0)
				profileImg = fservice.getProfileNull();
		else
				profileImg = fservice.getProfileImg(storeId);
		
		
		request.setAttribute("countAllProducts"	   , countAllProducts);
		request.setAttribute("countSoldoutProducts", countSoldoutProducts);
		request.setAttribute("storeRatingAvg", storeRatingAvg);
		request.setAttribute("profileImg", profileImg);
		request.setAttribute("storeVO", storeVO);
		request.setAttribute("storeId", storeId);
		request.getRequestDispatcher("/WEB-INF/store/mypage.jsp").forward(request, response);
	}

}
