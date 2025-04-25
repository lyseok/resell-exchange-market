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
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class SafeTXNBoard
 */
@WebServlet("/admin/safeTXNBoardView.do")
public class SafeTXNBoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		ISafeTXNBoardService service = SafeTXNBoardServiceImpl.getinstance();
		
		List<SafeTXNVO> lstvo = service.selectboardlist();
		System.out.println("서블릿 확인 :" + lstvo);
		Gson gson = new Gson();
		
		String json = gson.toJson(lstvo);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
			
		
	}
	

}
