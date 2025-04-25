package rem.admin.product.wishlist.contoller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.wishlist.service.IWishlistAllService;
import rem.admin.product.wishlist.service.WishlistAllServiceImpl;
import rem.admin.product.wishlist.vo.WishlistAllVO;


@WebServlet("/admin/serchWishlistAll.do")
public class SerchWishlistAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IWishlistAllService service = WishlistAllServiceImpl.getinstance();
		String select = request.getParameter("searchWishlist");
		String value = request.getParameter("searchText");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("select", select);
		map.put("value", value);
		
		System.out.println("select" + select);
		System.out.println(value);
		
		List<WishlistAllVO> wvoall = service.searchboardlist(map);
		
		request.setAttribute("wvoall", wvoall);
		request.getRequestDispatcher("/WEB-INF/admin/product/wishlist/wishlist_list.jsp").forward(request, response);
		
		
	}

}
