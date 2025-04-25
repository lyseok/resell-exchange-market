package rem.admin.board.faq.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.faq.service.FaqBoardServiceImpl;
import rem.admin.board.faq.service.IFaqBoardService;
import rem.admin.board.faq.vo.FAQBoardVO;

import java.io.IOException;

/**
 * Servlet implementation class InsertFAQ
 */
@WebServlet("/admin/faqInsert.do")
public class InsertFAQ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		
		IFaqBoardService service = FaqBoardServiceImpl.getinstance();
		
		String title = request.getParameter("faqTitle");
		String cont = request.getParameter("faqContent");
		String type = request.getParameter("faqType");
		
		System.out.println("나와라" + title);
		System.out.println(cont);
		System.out.println(type);
		
		FAQBoardVO fbvo = new FAQBoardVO();
		fbvo.setFaq_title(title);
		fbvo.setFaq_cont(cont);
		fbvo.setFaq_type(Integer.parseInt(type));
		System.out.println("서블릿 확인 : " + fbvo);
		
		int fbvov = service.insertFaq(fbvo);
		
		
		if(fbvov > 0) {
		
		response.sendRedirect(request.getContextPath() + "/admin/faqBoardView.do");
		//request.getRequestDispatcher("/WEB-INF/admin/board/faq/faqlist.jsp").forward(request, response);
		}
		}

}
