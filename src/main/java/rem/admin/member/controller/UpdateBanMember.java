package rem.admin.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.member.dao.MemberDaoImpl;
import rem.admin.member.service.IMemberService;
import rem.admin.member.service.MemberServiceImpl;

import java.io.IOException;

import com.google.gson.Gson;

@WebServlet("/admin/updateBanMember.do")
public class UpdateBanMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("mem_no"));
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		
		int res = service.banMember(no);
		
		response.getWriter().write(new Gson().toJson(res));
	}

}
