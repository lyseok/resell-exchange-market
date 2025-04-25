package rem.admin.product.review.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.review.service.IReviewService;
import rem.admin.product.review.service.ReviewServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;



@WebServlet("/admin/deleteReview.do")
public class DeleteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IReviewService service = ReviewServiceImpl.getInstence();
		
		// 맵퍼실행 성공 여부
		int res = service.deleteReviewModal(Integer.parseInt(request.getParameter("txn")));
		System.out.println(res + " >>> review 맵퍼실행 성공 여부");
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(res));
	}

}
