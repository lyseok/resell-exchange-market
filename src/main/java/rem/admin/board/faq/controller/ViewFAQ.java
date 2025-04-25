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
 * Servlet implementation class ViewFAQ
 */
@WebServlet("/admin/viewFAQ.do")
public class ViewFAQ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		
		
		IFaqBoardService service = FaqBoardServiceImpl.getinstance();
		
		String fano = request.getParameter("faq_no");
		
		System.out.println("파라미터 가져옴? : " + fano);
		
		
		int ifano = Integer.parseInt(fano);
		
		
		
		System.out.println("게시글읽기 서블릿 테스트1 : " + ifano);
		
		FAQBoardVO vfbvo = service.selectFaqView(ifano);
		
		System.out.println("게시글읽기 서블릿 테스트2 : " + vfbvo);
		
		
		request.setAttribute("vfbvo", vfbvo);
		
		request.getRequestDispatcher("/WEB-INF/admin/board/faq/viewFAQ.jsp").forward(request, response);
		
		
		
	}

}
