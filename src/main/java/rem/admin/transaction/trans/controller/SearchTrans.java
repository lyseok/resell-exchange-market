package rem.admin.transaction.trans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.main.vo.AdminTransVO;
import rem.admin.transaction.trans.dao.TransDaoImpl;
import rem.admin.transaction.trans.service.ITransService;
import rem.admin.transaction.trans.service.TransServiceImpl;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/searchTrans.do")
public class SearchTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("txn_no"));
		
		ITransService service = TransServiceImpl.getInstance(TransDaoImpl.getInstance());
		List<AdminTransVO> list = service.searchTrans(no);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(new Gson().toJson(list));
	}

}
