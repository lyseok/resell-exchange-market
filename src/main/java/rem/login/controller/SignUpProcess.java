package rem.login.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signUpProcess.do")
public class SignUpProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO vo = new MemberVO();
		vo.setMem_email(request.getParameter("email"));
		vo.setMem_pw(request.getParameter("password"));
		vo.setMem_name(request.getParameter("name"));
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		int res = service.insertMember(vo);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

}
