package rem.wishlist.controller;

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
import rem.wishlist.vo.WishlistVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


@WebServlet("/wishlist/countProdWish.do")
public class CountProdWish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		int memId = loginInfo.getMem_no();
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		IWishlistService service = WishlistServiceImpl.getInstance(WishlistDaoImpl.getInstance());
		WishlistVO wvo = new WishlistVO();
		
		wvo.setMem_no(memId);
		wvo.setProd_no(prod_no);
		int countProdWish = service.countProdWish(prod_no);
		int distinct = service.distinctWish(wvo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("countWish", countProdWish);
		map.put("distinct", distinct);
		
		Gson gson = new Gson();
		
		String result = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		
		out.println(result);
		out.flush();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
