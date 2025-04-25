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


@WebServlet("/admin/reviewModal.do")
public class ReviewModal extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=utf-8");
		
		IReviewService service =  ReviewServiceImpl.getInstence();
		ReviewImgVO vo = new ReviewImgVO();
		
		Integer txn = Integer.parseInt(request.getParameter("txn"));
		
		System.out.println("ReviewModal 파라미터가 잘 넘어오는 지 확인" + txn);
		
		vo = service.selectReviewModal(Integer.parseInt(request.getParameter("txn")));
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(vo));
		out.flush();
	}

}
