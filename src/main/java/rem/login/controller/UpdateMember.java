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

@WebServlet("/updateMember.do")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdateMemberVO vo = new UpdateMemberVO();
		vo.setMem_no(Integer.parseInt(request.getParameter("no")));
		vo.setMem_name(request.getParameter("email"));
		vo.setMem_pw(request.getParameter("pw"));
		vo.setMem_name(request.getParameter("name"));
		vo.setMem_tel(request.getParameter("tel"));
		vo.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		vo.setAdd1(request.getParameter("address"));
		vo.setAdd2(request.getParameter("address_detail"));
		
		IMemberService service = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		int res = service.updateMember(vo);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

}
