package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.vo.MemberVO;

import java.io.IOException;

@WebServlet("/main/report/wr.do")
public class MainReportWriteForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			response.sendRedirect("/WEB-INF/login/login.jsp");
			return;
		}
		
		 String prodNoStr = request.getParameter("prod_no");
		    String storeIdStr = request.getParameter("storeId");

		    if (prodNoStr != null && !prodNoStr.isEmpty()) {
		        int prodNo = Integer.parseInt(prodNoStr);
		        request.setAttribute("reportNo", prodNo);
		    }

		    if (storeIdStr != null && !storeIdStr.isEmpty()) {
		        int storeId = Integer.parseInt(storeIdStr);
		        request.setAttribute("reportNo", storeId);
		    }
		
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainReportForm.jsp").forward(request, response);
	}

	

}
