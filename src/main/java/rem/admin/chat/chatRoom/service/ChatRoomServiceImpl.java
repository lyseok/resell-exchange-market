package rem.admin.chat.chatRoom.service;

import java.util.List;
import java.util.Map;

import rem.admin.chat.chatRoom.dao.ChatRoomDaoImpl;
import rem.admin.chat.chatRoom.dao.IChatRoomDao;
import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;

public class ChatRoomServiceImpl implements IChatRoomService {
	private static ChatRoomServiceImpl service;
	
	private IChatRoomDao dao;
	
	private ChatRoomServiceImpl () {
		dao = ChatRoomDaoImpl.getInstence();
	}
	
	public static ChatRoomServiceImpl getInstence() {
		if(service==null) service = new ChatRoomServiceImpl();
		return service;
	}

	@Override
	public List<ChatRoomVO> selectChatRoomAll() {
		return dao.selectChatRoomAll();
	}

	@Override
	public List<ChatMemberVO> selectChatMemberAll(String roomNo) {
		return dao.selectChatMemberAll(roomNo);
	}

	@Override
	public List<ChatRoomVO> chatRoomSearch(Map<String, String> map) {
		return dao.chatRoomSearch(map);
	}

	@Override
	public List<ChatMemberVO> chatMemSearch(Map<String, String> map) {
		return dao.chatMemSearch(map);
	}


}
