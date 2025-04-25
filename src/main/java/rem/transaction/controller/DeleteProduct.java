package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/deleteProduct.do")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MemberVO loginInfo = (MemberVO) request.getSession().getAttribute("loginInfo");
	    if (loginInfo == null) {
	        response.sendRedirect("/loginPage.jsp");
	        return;
	    }
	    int memberNo = loginInfo.getMem_no(); 
		
		
		
		System.out.println("로그인된 회원번호" + memberNo);
		
		int prodNo =  Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("삭제할 상품 번호" + prodNo);
	
		
		ITransactionService service = TransactionServiceImpl.getInstance();
		
		
		ProductVO prodVo = new ProductVO();
		
		prodVo.setMem_no(memberNo);
		prodVo.setProd_no(prodNo);
		int result = service.deleteProd(prodVo);
		
		System.out.println("result: " + result);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
