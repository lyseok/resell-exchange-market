package rem.login.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.UpdateMemberVO;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

@WebServlet("/selectUpdateMember.do")
public class SelectUpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		
		int no = Integer.parseInt(request.getParameter("no"));
		UpdateMemberVO vo = service.selectUpdateMember(no);
		 
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(vo));
		out.flush();		
	}

}
