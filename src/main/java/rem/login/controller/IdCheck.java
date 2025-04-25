package rem.login.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/idCheck.do")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		int res = service.idCheck(email);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}
}
