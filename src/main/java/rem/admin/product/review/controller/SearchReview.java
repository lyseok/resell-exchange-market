package rem.admin.product.review.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.review.service.IReviewService;
import rem.admin.product.review.service.ReviewServiceImpl;
import rem.review.vo.ReviewImgVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchReview
 */
@WebServlet("/admin/searchReview.do")
public class SearchReview extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;utf-8");
		
		IReviewService service = ReviewServiceImpl.getInstence();
		
		
		// 파라미터로 보내줄 값을 꺼내와서 세팅함
		String select = request.getParameter("select");
		String value = request.getParameter("value");
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("select", select);
		map.put("value", value);
		
		// 세팅한 값을 파라미터로 보낸 후 수행한 결과값을 List에 저장
		List<ReviewImgVO> list = service.searchReview(map);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
	}

}
