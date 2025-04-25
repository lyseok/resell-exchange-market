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
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/reviewList.do")
public class ReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/product/review/reviewList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		IReviewService service =  ReviewServiceImpl.getInstence();
		List<ReviewImgVO> list = new ArrayList<ReviewImgVO>();
		
		list = service.selectReviewList();
		
		request.setAttribute("rvList", list);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(list));
		out.flush();
	}

}
