package rem.admin.board.qna.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.board.qna.service.IQnaService;
import rem.admin.board.qna.service.QnaServiceImpl;
import rem.admin.board.qna.vo.QnaBoardVO;

import java.io.IOException;


@WebServlet("/admin/qnaViewPage.do")
public class QnaViewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IQnaService service = QnaServiceImpl.getInstance();
		
		// jsp에서 주소에 파라미터 값으로 넘겨준 애를 여기서 꺼내서 qnaNum에 담음
		String qnaNum = request.getParameter("qnaNo");

		// 위에서 저장한값(문자)를 숫자로 형변환 후에 파라미터 값으로 넘겨준 후 vo에 저장함
		QnaBoardVO vo = service.seletQnaView(Integer.parseInt(qnaNum));
		request.setAttribute("qnaVo", vo);
		
		request.getRequestDispatcher("/WEB-INF/admin/board/qna/qnaView.jsp").forward(request, response);
	}

}
