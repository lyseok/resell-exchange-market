package rem.admin.member.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.member.dao.MemberDaoImpl;
import rem.admin.member.service.IMemberService;
import rem.admin.member.service.MemberServiceImpl;
import rem.login.vo.MemberVO;

@WebServlet("/admin/selectAllMemberList.do")
public class SelectAllMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		List<MemberVO> list = service.selectAllMemberList();
		
		String json = new Gson().toJson(list);
		
		request.setAttribute("result", json);
		request.getRequestDispatcher("/WEB-INF/admin/admin_member.jsp").forward(request, response);
	}

}
