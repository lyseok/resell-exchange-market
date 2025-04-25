package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/updateProdStatus.do")
public class UpdateProdStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String newStatus = request.getParameter("newStatus");
		ITransactionService service = TransactionServiceImpl.getInstance();
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		System.out.println("상품번호 : " + prodNo );
		
		
		int status = 0;
		int count = 0;
		
		if (newStatus.equals("판매중")) {
			param.put("prodNo", prodNo);
			param.put("newStatus", status);
			
			count = service.updateProdStatus(param);
		}
		else if(newStatus.equals("판매완료")) {
			status  = 1;
			param.put("prodNo", prodNo);
			param.put("newStatus", status);
			
			count = service.updateProdStatus(param);
		}
		else if(newStatus.equals("예약중")) {
			status = 2;
			param.put("prodNo", prodNo);
			param.put("newStatus", status);
			
			count = service.updateProdStatus(param);
		}
		
		
		
		if (count > 0) {
			PrintWriter out = response.getWriter();
			out.print("{\"status\" : \"ok\"}");
		}
		
		
	}

}
