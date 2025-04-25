package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

@WebServlet("/buyConfrimation.do")
public class BuyConfrimation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int txnNo = Integer.parseInt(request.getParameter("txnNo"));
		ITransactionService service = TransactionServiceImpl.getInstance();
		
		int count = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("txnNo", txnNo);
		param.put("status", 2);
		count = service.updateConfrimProd(param);
		
		
		
		JsonObject jsonRes = new JsonObject();
	
		
		if (count > 0) {
			
			jsonRes.addProperty("status", "success");
		}else {
			jsonRes.addProperty("status", "error");
		}
		response.getWriter().write(jsonRes.toString());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
