package rem.admin.transaction.safetransaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.transaction.safetransaction.service.ISafeTXNBoardService;
import rem.admin.transaction.safetransaction.service.SafeTXNBoardServiceImpl;
import rem.admin.transaction.safetransaction.vo.SafeTXNVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

/**
 * Servlet implementation class SafeTXNBoardModal
 */
@WebServlet("/admin/safeTXNBoardModal.do")
public class SafeTXNBoardModal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=utf-8");
		
		ISafeTXNBoardService service = SafeTXNBoardServiceImpl.getinstance();
		
		SafeTXNVO vo = new SafeTXNVO();
		
		Integer stvo = Integer.parseInt(request.getParameter("stvo"));
		
		System.out.println("ReviewModal 파라미터가 잘 넘어오는 지 확인" + stvo);
		
		vo = service.searchmodal(Integer.parseInt(request.getParameter("stvo")));
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(vo));
		out.flush();
		
		
	}

}
