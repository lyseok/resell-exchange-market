package rem.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import rem.chat.service.ChatServiceImpl;
import rem.chat.service.IChatService;
import rem.chat.vo.MessageVO;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.dao.MemberDaoImpl;
import rem.login.service.IMemberService;
import rem.login.service.MemberServiceImpl;
import rem.login.vo.MemberVO;

@ServerEndpoint(value="/chatSocketbasic.do", configurator=WebSocketConfigurator.class)
public class ChatSocket {

	 private static Map<Integer, Session> userSessionMap = new ConcurrentHashMap<>();
	 private int userId;
	 IChatService service = ChatServiceImpl.getInstance();
	 IFileService fservice = FileServiceImpl.getInstance();
	 IMemberService memservice= MemberServiceImpl.getInstance(MemberDaoImpl.getInstance());
	 MessageVO mvo = new MessageVO();
	
	@OnOpen
	public void handleOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession)config.getUserProperties().get("httpSession");
		MemberVO loginInfo = (MemberVO)httpSession.getAttribute("loginInfo");
		this.userId = loginInfo.getMem_no();
		userSessionMap.put(userId, session);
		
		session.setMaxIdleTimeout(30 * 60 * 1000);
		System.out.println("webSocket 연결됨 : userId : " + userId);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			JsonObject obj = JsonParser.parseString(message).getAsJsonObject();
			int chat_room_no = obj.get("chat_room_no").getAsInt();
			HttpSession httpSession = (HttpSession)session.getUserProperties().get("httpSession");
			MemberVO loginInfo = (MemberVO)httpSession.getAttribute("loginInfo");
			int userId = loginInfo.getMem_no();
			String msg_cont = obj.get("msg_cont").getAsString();
			mvo.setChat_room_no(chat_room_no);
			mvo.setMem_no(userId);
			mvo.setMsg_cont(msg_cont);
			System.out.println(userId);
			
			service.sendMessage(mvo);
			ImgFileVO fvo = fservice.getProfileImg(userId);
			MemberVO memvo = memservice.getMemInfo(userId);
			
            JsonObject response = new JsonObject();
            response.addProperty("from", userId);
            response.addProperty("chat_room_no", chat_room_no);
            response.addProperty("msg_cont", msg_cont);
            response.addProperty("file_path", fvo.getFile_path());
            response.addProperty("mem_alias", memvo.getMem_alias());

            for (Session connectedSession : userSessionMap.values()) {
                try {
                    connectedSession.getBasicRemote().sendText(response.toString());
                    System.out.println("전송: " + response.toString() + " to Session ID: " + connectedSession.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                    // 전송 실패 시 처리 (예: 해당 세션 닫기, 오류 로깅 등)
                    try {
                        connectedSession.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 @OnClose
	    public void onClose(Session session) {
	        userSessionMap.remove(this.userId);
	        System.out.println("WebSocket 종료됨: 사용자 ID = " + userId);
	    }

	    @OnError
	    public void onError(Session session, Throwable throwable) {
	        System.err.println("WebSocket 오류 발생: " + throwable.getMessage());
	    }
}
