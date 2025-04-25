package rem.chat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;
import rem.chat.vo.ChatVO;
import rem.chat.vo.MessageVO;

public interface IChatDao {
	public List<ChatVO> getAllChatuser(int mem_no);
	
	public List<ChatVO> getAllChatroom(int mem_no);
	
	public List<MessageVO> getMessage(int chat_room_no);
	
	public int insertChatroom(ChatRoomVO vo);
	
	public int insertChatmember(ChatVO vo);
	
	public int countChatroom(Map<String, Object> map);
	
	public int sendMessage(MessageVO vo);
}
