package rem.admin.chat.chatRoom.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.chat.chatRoom.service.ChatRoomServiceImpl;
import rem.admin.chat.chatRoom.service.IChatRoomService;
import rem.chat.vo.ChatRoomVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


@WebServlet("/admin/chatRoomSearch.do")
public class ChatRoomSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		IChatRoomService service = ChatRoomServiceImpl.getInstence();
		
		String select = request.getParameter("select");
		String value = request.getParameter("value");
		System.out.println("select값 확인" + select);
		System.out.println("value 값 확인" + value);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", value);
		map.put("select", select);
		
		List<ChatRoomVO> list = service.chatRoomSearch(map);
		System.out.println("값 찍히는 지 확인" + list);
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
	}

}
