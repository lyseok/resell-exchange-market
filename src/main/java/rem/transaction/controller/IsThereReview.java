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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/isThereReview.do")
public class IsThereReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] transactions = request.getParameterValues("list");
		System.out.println("■■■■■■■■■■■■■■■■■■■ 음흠흠흠 list 가져오기 ==> transactions: " + transactions);
		Map<String, Integer> map = new HashMap<>();
	    
		
		for(String v: transactions) {
			ITransactionService service = TransactionServiceImpl.getInstance();
			int rec = service.isThereReview(Integer.parseInt(v));
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■IsThereReview.java ==> v & rec: " +v+", "+ rec);
			map.put(String.valueOf(v), rec);
			
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = gson.toJson(map);
		System.out.println("■■■■■■■■■■■■■■■■■■■■■ result ==> "+ result);
	    response.setContentType("application/json;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(result); 
	    out.flush();
	}

}
