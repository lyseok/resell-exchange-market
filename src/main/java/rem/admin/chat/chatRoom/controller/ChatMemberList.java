package rem.admin.chat.chatRoom.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.admin.chat.chatRoom.service.ChatRoomServiceImpl;
import rem.admin.chat.chatRoom.service.IChatRoomService;
import rem.chat.vo.ChatMemberVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/chatMemberList.do")
public class ChatMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		IChatRoomService service = ChatRoomServiceImpl.getInstence();
		
		List<ChatMemberVO> list = service.selectChatMemberAll(request.getParameter("roomNo"));
		
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.flush();
		
	}

}
