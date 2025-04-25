package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.faq.service.FaqBoardServiceImpl;
import rem.admin.board.faq.service.IFaqBoardService;
import rem.admin.board.faq.vo.FAQBoardVO;
import rem.login.vo.MemberVO;

import java.io.IOException;

@WebServlet("/main/faq/view.do")
public class MainFAQView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		int faq_no = Integer.parseInt(request.getParameter("faqNo"));
		IFaqBoardService service = FaqBoardServiceImpl.getinstance();
		FAQBoardVO vo = service.selectFaqView(faq_no);
		
		String faq_title = vo.getFaq_title();
		String faq_cont = vo.getFaq_cont();
		String faq_at = vo.getFaq_at();
		int faq_type = vo.getFaq_type();   
		
		request.setAttribute("faq_no", faq_no);
		request.setAttribute("faq_title", faq_title);
		request.setAttribute("faq_cont", faq_cont);
		request.setAttribute("faq_at", faq_at);
		request.setAttribute("faq_type", faq_type);
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainFAQView.jsp").forward(request, response);
		
	}
}
