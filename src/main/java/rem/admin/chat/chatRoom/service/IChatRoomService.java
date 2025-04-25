package rem.admin.chat.chatRoom.service;

import java.util.List;
import java.util.Map;

import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;

public interface IChatRoomService {
	public List<ChatRoomVO> selectChatRoomAll();
	
	public List<ChatMemberVO> selectChatMemberAll(String roomNo);
	
	public List<ChatRoomVO> chatRoomSearch(Map<String, String> map);

	public List<ChatMemberVO> chatMemSearch(Map<String, String> map);
}
