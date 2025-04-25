package rem.admin.board.faq.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.faq.service.FaqBoardServiceImpl;
import rem.admin.board.faq.service.IFaqBoardService;

import java.io.IOException;


@WebServlet("/admin/deleteFAQ.do")
public class DeleteFAQ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IFaqBoardService service = FaqBoardServiceImpl.getinstance();
		
		String fano = request.getParameter("faq_no");
		
		System.out.println("삭제서블릿 데이터 확인" + fano);
		
		int dfbvo = service.deleteFaq(Integer.parseInt(fano));
		
		response.sendRedirect(request.getContextPath() + "/admin/faqBoardView.do");
	}

}
