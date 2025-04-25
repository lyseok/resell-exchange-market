package rem.search.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.product.vo.ProdImgVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

@WebServlet("/searchPage.do")
public class SearchPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String searchText = request.getParameter("searchText");
		
		ISearchService service = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		List<ProdImgVO> list = service.selectSearchProduct(searchText);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		request.setAttribute("result", json);
		request.setAttribute("seasrchText", searchText);
		request.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(request, response);
	}

}
