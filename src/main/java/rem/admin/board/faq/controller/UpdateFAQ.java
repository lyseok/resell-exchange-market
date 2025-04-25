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


@WebServlet("/admin/updateFAQ.do")
public class UpdateFAQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IFaqBoardService service = FaqBoardServiceImpl.getinstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String num = request.getParameter("faqNo");
		FAQBoardVO ifbvo = service.selectFaqView(Integer.parseInt(num));
		
		
		request.setAttribute("ifbvo", ifbvo);
		
		request.getRequestDispatcher("/WEB-INF/admin/board/faq/updateFAQ.jsp").forward(request, response);
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//		request.setCharacterEncoding("utf-8");
//		
//		
//		
//		String num = request.getParameter("faqNo");
//		String title = request.getParameter("faqTitle");
//		String cont = request.getParameter("faqCont");
//		String type = request.getParameter("faqType");
//		
//		System.out.println("수정서블릿post 체크 :" + cont);
//		
//		int fbnum = Integer.parseInt(num);
//		int ftype = Integer.parseInt(type);
//		FAQBoardVO fbvo = new FAQBoardVO();
//		fbvo.setFaq_no(fbnum);
//		fbvo.setFaq_title(title);
//		fbvo.setFaq_cont(cont);
//		fbvo.setFaq_type(ftype);
//		
//		int lfbvo = service.updateFaq(fbvo);
//		
//		response.sendRedirect(request.getContextPath() + "/admin/faqBoardView.do?faq_no" + fbnum);
//		
//		
//		
//		
//		
//		
//	}

}
