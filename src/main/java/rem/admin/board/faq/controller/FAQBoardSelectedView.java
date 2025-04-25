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
import java.util.List;

/**
 * Servlet implementation class FAQBoardSelectedView
 */
@WebServlet("/admin/faqBoardSelectedView.do")
public class FAQBoardSelectedView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		IFaqBoardService service = FaqBoardServiceImpl.getinstance();
		
		String type = request.getParameter("faqType");
		System.out.println(type);
		List<FAQBoardVO> fbvo = service.selectByType(type);
	
		
		System.out.println("타입 확인" + type);
		System.out.println("FAQ리스트에 전달 할 글목록 : " + fbvo);
		
		request.setAttribute("fbvo", fbvo);
		request.setAttribute("selected", type);
		request.getRequestDispatcher("/WEB-INF/admin/board/faq/faqlist.jsp").forward(request, response);
	}

}
