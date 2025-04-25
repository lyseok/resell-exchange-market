package rem.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet("/loginProcess.do")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("inEmail");
		String password = request.getParameter("inPassword");
		System.out.println(email);
		System.out.println(password);
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("mem_email", email);
		map.put("mem_pw", password);
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		
		MemberVO  vo = service.login(map);
		
		HttpSession hSession = request.getSession();
		// String page = request.getContextPath(); // 조건에 따른 페이지 경로
		String res = "";
		if(vo != null) { // 로그인에 성공한 경우
			hSession.setAttribute("loginInfo", vo);
			hSession.setAttribute("login", "true");
			
			if(vo.getMem_lv() == 0) { // 회원의 권한이 일반회원인 경우
				res = "member";
			} else { // 회원의 권한이 관리자인 경우
				res = "admin";
			}
		} else { // 로그인에 실패한 경우
			hSession.setAttribute("login", "false");
			res = "false";
		}
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();

	}

}
