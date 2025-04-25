package rem.admin.transaction.safetransaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.transaction.safetransaction.service.ISafeTXNBoardService;
import rem.admin.transaction.safetransaction.service.SafeTXNBoardServiceImpl;
import rem.admin.transaction.safetransaction.vo.SafeTXNVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class SafeTXNBoardSearch
 */
@WebServlet("/admin/safeTXNBoardSearch.do")
public class SafeTXNBoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		System.out.println("여기까지는 왓엉 ㅋ");
		String searchSafeTxn = request.getParameter("searchSafeTXN");
		String value = request.getParameter("searchText");
		
		
		System.out.println("searchSafeTxn =================" + searchSafeTxn);
		System.out.println("searchText =================" + value);
		ISafeTXNBoardService service = SafeTXNBoardServiceImpl.getinstance();
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchSafeTxn", searchSafeTxn);
		map.put("value", value);
		
		List<SafeTXNVO> sstvo = service.searchboardlist(map);
		
		System.out.println("list=========================" + sstvo );
		Gson gson = new Gson();
		String json = gson.toJson(sstvo);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
		
		
	}

}
