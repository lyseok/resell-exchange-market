package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;
import rem.transaction.vo.ShippingVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/getTrackInfo.do")
public class GetTrackInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int txnNo = Integer.parseInt(request.getParameter("txnNo"));
		
		ITransactionService service = TransactionServiceImpl.getInstance();
		ShippingVO svo = new ShippingVO();
		svo = service.getTrackInfo(txnNo);
		JsonObject jsonRes = new JsonObject();
		
		response.setContentType("application/json;charset=UTF-8");
		
		
		/*
		Gson gson = new Gson();
		String result = gson.toJson(svo);
	    PrintWriter out = response.getWriter();
	    out.print(result); 
	    out.flush();
		*/
		
		if (svo ==null || svo.getShip_porv() == null || svo.getTrack_num() == null ) {   //svo객체를 먼저 null 검사해서 다음 조건을 무시하게한다. 조건문의 위치 중요
			jsonRes.addProperty("status","null" );
			jsonRes.addProperty("infoNull","배송정보 입력 전입니다." );
		}
		else {
			jsonRes.addProperty("status","notNull" );
			jsonRes.addProperty("ship_porv", svo.getShip_porv());
			jsonRes.addProperty("track_num", svo.getTrack_num());
		}
		
		response.getWriter().write(jsonRes.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
