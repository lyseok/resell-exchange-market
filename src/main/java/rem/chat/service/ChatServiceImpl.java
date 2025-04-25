package rem.chat.service;

import java.util.List;
import java.util.Map;

import rem.chat.dao.ChatDaoImpl;
import rem.chat.dao.IChatDao;
import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;
import rem.chat.vo.ChatVO;
import rem.chat.vo.MessageVO;

public class ChatServiceImpl implements IChatService {
	private IChatDao dao;
	
	private static ChatServiceImpl service;
	
	private ChatServiceImpl() {
		dao = ChatDaoImpl.getInstance();
	}
	
	public static ChatServiceImpl getInstance() {
		if(service==null) service = new ChatServiceImpl();
		return service;
	}

	@Override
	public List<ChatVO> getAllChatuser(int mem_no) {
		// TODO Auto-generated method stub
		return dao.getAllChatuser(mem_no);
	}

	@Override
	public List<ChatVO> getAllChatroom(int mem_no) {
		// TODO Auto-generated method stub
		return dao.getAllChatroom(mem_no);
	}

	@Override
	public List<MessageVO> getMessage(int chat_room_no) {
		// TODO Auto-generated method stub
		return dao.getMessage(chat_room_no);
	}

	@Override
	public int insertChatroom(ChatRoomVO vo) {
		// TODO Auto-generated method stub
		return dao.insertChatroom(vo);
	}

	@Override
	public int insertChatmember(ChatVO vo) {
		// TODO Auto-generated method stub
		return dao.insertChatmember(vo);
	}

	@Override
	public int countChatroom(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.countChatroom(map);
	}

	@Override
	public int sendMessage(MessageVO vo) {
		// TODO Auto-generated method stub
		return dao.sendMessage(vo);
	}

}
