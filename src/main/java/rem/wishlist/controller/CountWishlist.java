package rem.wishlist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;
import rem.wishlist.dao.WishlistDaoImpl;
import rem.wishlist.service.IWishlistService;
import rem.wishlist.service.WishlistServiceImpl;

@WebServlet("/wishlist/countWishlist.do")
public class CountWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IWishlistService service = WishlistServiceImpl.getInstance(WishlistDaoImpl.getInstance());
		int res = 0;
		
		if(session.getAttribute("loginInfo") != null) {
			MemberVO memberInfo = (MemberVO)session.getAttribute("loginInfo");
			res = service.countWishlist(memberInfo.getMem_no());
		}
		
		// 회원번호를 받아서 int로 변환
		
		PrintWriter out = response.getWriter();
		
		out.print(res);
		out.flush();
	}

}
