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


@WebServlet("/upProduct.do")
public class UpProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO loginInfo = (MemberVO) request.getSession().getAttribute("loginInfo");
	    if (loginInfo == null) {
	        response.sendRedirect("/loginPage.jsp");
	        return;
	    }
	    int memberNo = loginInfo.getMem_no(); 
		
	    System.out.println("로그인된 회원의 번호 :" + memberNo);
	    
	    int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	    System.out.println("끌올할 상품 번호" + prodNo);
	    
	    
	    ProductVO prodVo = new ProductVO();
	    
	    prodVo.setProd_no(prodNo);
	    prodVo.setMem_no(memberNo);
	    
	    ITransactionService service = TransactionServiceImpl.getInstance();
	    int count = service.updateNewing(prodVo);
	   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
