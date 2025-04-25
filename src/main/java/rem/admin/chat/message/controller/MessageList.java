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
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/admin/messageList.do")
public class MessageList extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/chat/message/messageList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		IMessageService service = MessageServiceImpl.getInstance();
		
		List<MessageVO> msgList = service.selectMessageList();
		
		request.setAttribute("msglist", msgList);
		
		Gson gson = new Gson();
		
		String res = gson.toJson(msgList);	
		
		PrintWriter out = response.getWriter();
		
		
		out.print(res);
		out.flush();
	}

}
