package rem.admin.product.wishlist.contoller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.wishlist.service.IWishlistAllService;
import rem.admin.product.wishlist.service.WishlistAllServiceImpl;
import rem.admin.product.wishlist.vo.WishlistAllVO;


@WebServlet("/admin/wishlistPage.do")
public class WishlistAllPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		IWishlistAllService service = WishlistAllServiceImpl.getinstance();
		System.out.println(service);
		
		List<WishlistAllVO> wvoall = service.selectboardlist();
		System.out.println("bvo객체 확인" + service);
		System.out.println(wvoall);
		
		request.setAttribute("wvoall", wvoall);
		System.out.println("보낼수있을까?" + wvoall);
		request.getRequestDispatcher("/WEB-INF/admin/product/wishlist/wishlist_list.jsp").forward(request, response);
	}

}
