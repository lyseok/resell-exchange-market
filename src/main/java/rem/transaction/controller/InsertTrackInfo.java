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

import com.google.gson.Gson;


@WebServlet("/insertTrackInfo.do")
public class InsertTrackInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shipPorv = request.getParameter("shipPorv");
		String trackNum = request.getParameter("trackNum");
		int txnNo  = Integer.parseInt(request.getParameter("txnNo"));
		
		int count = 0;
		
		ShippingVO shipVo = new ShippingVO();
		
		shipVo.setShip_porv(shipPorv);
		shipVo.setTrack_num(trackNum);
		shipVo.setTxn_no(txnNo);
		
		
		System.out.println("shipVo" + shipVo);
		
		ITransactionService service = TransactionServiceImpl.getInstance();
		
		count = service.insertTrackInfo(shipVo);
		
		System.out.println(count);
		
		 response.setContentType("application/json");
	        if (count > 0) {
	            response.getWriter().write("{\"status\": \"success\"}");
	        } else {
	            response.getWriter().write("{\"status\": \"error\", \"message\": \"운송장 정보 입력 실패\"}");
	        }
		
	}

}
