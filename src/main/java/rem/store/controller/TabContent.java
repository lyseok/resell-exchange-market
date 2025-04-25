package rem.store.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.product.vo.ProdImgVO;
import rem.review.vo.ReviewImgVO;
import rem.store.service.IStoreService;
import rem.store.service.StoreServiceImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/store/tabContent.do")
public class TabContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int storeId = Integer.parseInt((String)request.getParameter("storeId"));
		String tabName = (String)request.getParameter("tabName");
		System.out.println("■■■■■TabContent.servlet ==> storeId : " +storeId);
		System.out.println("■■■■■TabContent.servlet ==> tabName : " +tabName);

		IStoreService service = StoreServiceImpl.getInstance();
		
		List<ProdImgVO> prodList = new ArrayList<>();
		List<ReviewImgVO> reviewList = new ArrayList<>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = "";
		
		switch(tabName) {
		case "tabProd"	  : 
			prodList = service.getStoreProdList(storeId);
			json = gson.toJson(prodList);
			break;
		case "tabReview"  : 
			reviewList = service.getStoreReviewList(storeId);
			json = gson.toJson(reviewList);
			break;
		case "tabSoldout" : 
			prodList = service.getStoreSoldoutList(storeId);
			json = gson.toJson(prodList);
			break;
		case "tabWishlist": 
			prodList = service.getStoreWishlist(storeId);
			json = gson.toJson(prodList);
			break;
		}

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
}
