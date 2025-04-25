package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.ProductVO;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;
import rem.transaction.vo.TransactionVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/product/insertTransaction.do")
public class InsertTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		
		ITransactionService tservice = TransactionServiceImpl.getInstance();
		IProductService pservice = ProductServiceImpl.getInstance();
		TransactionVO tvo = new TransactionVO();
		int prod_no = Integer.parseInt(request.getParameter("prod_no"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mem_no", loginInfo.getMem_no());
		param.put("prod_no", prod_no);
		
		ProductVO pvo = pservice.getProductDetail(param);
		
		
		tvo.setProd_no(prod_no); tvo.setMem_no(loginInfo.getMem_no()); 
		int count =tservice.insertTransaction(tvo);
		 
		if(count >0) { 
		
		int txn_no = tvo.getTxn_no();
		pservice.updateProdStatus(prod_no);
		 
		request.setAttribute("trans", txn_no);
		request.setAttribute("pvo", pvo);
		  
		}
		 
		request.getRequestDispatcher("/WEB-INF/product/transaction.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
