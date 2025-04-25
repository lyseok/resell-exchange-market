package rem.login.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/passwordCheck.do")
public class PasswordCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("pw");
		System.out.println(pw);
		
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", vo.getMem_email());
		map.put("pw", pw);
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		
		int res = service.pwCheck(map);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

}
