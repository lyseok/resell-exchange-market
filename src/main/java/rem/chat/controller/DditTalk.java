package rem.chat.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.chat.service.ChatServiceImpl;
import rem.chat.service.IChatService;
import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;
import rem.chat.vo.ChatVO;
import rem.chat.vo.MessageVO;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.MemberVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

@WebServlet("/chat/dditTalk.do")
public class DditTalk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		int memId = loginInfo.getMem_no();
		
		IMemberService mservice = MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
		IChatService service = ChatServiceImpl.getInstance();
		int countRoom = 0;
		ChatRoomVO rvo = new ChatRoomVO();
		ChatVO cvo = new ChatVO();
		rvo.setMem_no(memId);
		rvo.setChat_name(loginInfo.getMem_alias());
		
		String mem_no = request.getParameter("mem_no");
		int prod_mem_no = 0;
		if(mem_no != null && !mem_no.trim().isEmpty()) {
			try {
			prod_mem_no = Integer.parseInt(mem_no);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_no", memId);
			map.put("chat_user", prod_mem_no);
			countRoom = service.countChatroom(map);
			
			if(countRoom == 0) {
				int cnt = service.insertChatroom(rvo);
				if(cnt > 0) {
					cvo.setChat_user(prod_mem_no);
					cvo.setChat_room_no(rvo.getChat_room_no());
					service.insertChatmember(cvo);
				}
			}
			
			}catch (NumberFormatException e) {
		        System.out.println("잘못된 mem_no 파라미터: " + mem_no);
		        // Optional: 기본 로직만 실행하고 무시할 수도 있음
		    }
		}
		Map<String, Object> room = new HashMap<String, Object>();
		room.put("mem_no", memId);
		room.put("chat_user", prod_mem_no);
		int room_no = service.countChatroom(room);
		
		List<ChatVO> userlist = new ArrayList<ChatVO>();
		List<ChatVO> roomlist = new ArrayList<ChatVO>();
	
		userlist = service.getAllChatuser(memId);
		roomlist = service.getAllChatroom(memId);
		
		MemberVO mvo = mservice.getMemInfo(prod_mem_no);
		
		request.setAttribute("mvo", mvo);
		request.setAttribute("room_no", room_no);
		request.setAttribute("userlist", userlist);
		request.setAttribute("roomlist", roomlist);
		request.getRequestDispatcher("/WEB-INF/chat/dditTalk.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int chat_room_no = Integer.parseInt(request.getParameter("chat_room_no"));

		IChatService service = ChatServiceImpl.getInstance();

		List<MessageVO> mlist = service.getMessage(chat_room_no);

		Gson gson = new Gson();

		String result = gson.toJson(mlist);

		PrintWriter out = response.getWriter();

		out.println(result);
		out.flush();
	}

}
