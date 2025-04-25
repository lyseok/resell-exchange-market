package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;
import rem.transaction.vo.ProdTransactionVO;
import rem.transaction.vo.TransactionImgVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;


@WebServlet("/getProdInfo.do")
public class GetProdInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//@SuppressWarnings("unChecked");
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int txnNo = Integer.parseInt(request.getParameter("txnNo"));
		System.out.println(txnNo);
		
		ITransactionService service = TransactionServiceImpl.getInstance();
		
		TransactionImgVO pvo = new TransactionImgVO ();
		
		pvo = service.getProdInfo(txnNo);
		
		Gson gson = new Gson();
	

		String result = gson.toJson(pvo);

		response.setContentType("application/json;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(result); 
	    out.flush();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
