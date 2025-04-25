package rem.deposit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.deposit.dao.DepositDaoImpl;
import rem.deposit.service.DepositServiceImpl;
import rem.deposit.service.IDepositService;
import rem.deposit.vo.DepositVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

@WebServlet("/insertDeposit.do")
public class InsertDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DepositVO vo = new DepositVO();
		vo.setTfr_mem_no(Integer.parseInt(request.getParameter("mem_no")));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setTfr_cont(request.getParameter("cont"));
		
		IDepositService service = DepositServiceImpl.getInstance(DepositDaoImpl.getInstance());
		int res = service.insertDeposit(vo);
		System.out.println(res);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(res));
		out.flush();
	}

}
