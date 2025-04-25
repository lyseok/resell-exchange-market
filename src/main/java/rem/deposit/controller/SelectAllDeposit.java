package rem.deposit.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.deposit.dao.DepositDaoImpl;
import rem.deposit.service.DepositServiceImpl;
import rem.deposit.service.IDepositService;
import rem.deposit.vo.DepositVO;

@WebServlet("/selectAllDeposit.do")
public class SelectAllDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;carset=utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		IDepositService service = DepositServiceImpl.getInstance(DepositDaoImpl.getInstance());
		List<DepositVO> list = null;
		
		list = service.selectAllDeposit(no);
		
		response.getWriter().write(new Gson().toJson(list));
	}

}
