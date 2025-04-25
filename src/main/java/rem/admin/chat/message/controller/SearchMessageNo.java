package rem.admin.chat.message.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.chat.message.service.IMessageService;
import rem.admin.chat.message.service.MessageServiceImpl;
import rem.chat.vo.MessageVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


@WebServlet("/admin/searchMessageNo.do")
public class SearchMessageNo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IMessageService service = MessageServiceImpl.getInstance();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", request.getParameter("value"));
		map.put("select", request.getParameter("select"));
		
		
		List<MessageVO> list = service.searchMessage(map);
		System.out.println(list + "메세지 리스트 테스트다 임마!!!!");
		
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
	}

}
