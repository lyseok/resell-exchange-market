package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.product.review.service.IReviewService;
import rem.review.service.ReviewServiceImpl;
import rem.review.vo.ReviewImgVO;
import rem.review.vo.ReviewVO;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;
import rem.transaction.vo.ReviewTransactionVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;


@WebServlet("/getReview.do")
public class GetReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int txnNo = Integer.parseInt(request.getParameter("txnNo"));
		System.out.println("■■■■■■■■■■■■■■■■GetReview.java ==> txnNo: " + txnNo);
		
		ITransactionService service = TransactionServiceImpl.getInstance();
		ReviewImgVO rvo = new ReviewImgVO();
		
		rvo = service.getReviewInfo(txnNo);
		System.out.println("rvo야 들어와줘!!!" + rvo);
		
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		
		if (rvo.getReview_cont() != null/* && rvo.getReview_rating() != 0 */) {
			String result = gson.toJson(rvo);

			
		    PrintWriter out = response.getWriter();
		    out.print(result); 
		    out.flush();
		}
		
		
		
	}

}
