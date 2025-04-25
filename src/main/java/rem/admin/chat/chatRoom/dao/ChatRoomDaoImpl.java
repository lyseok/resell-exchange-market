package rem.admin.chat.chatRoom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;
import utill.MyBatisUtil;

public class ChatRoomDaoImpl implements IChatRoomDao {
	private static ChatRoomDaoImpl dao;
	
	private ChatRoomDaoImpl() {}
	
	public static ChatRoomDaoImpl getInstence() {
		if(dao==null) dao = new ChatRoomDaoImpl();
		return dao;
	}
	
	@Override
	public List<ChatRoomVO> selectChatRoomAll() {
		SqlSession session = null;
		List<ChatRoomVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admCRoom.selectChatRoomAll");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ChatMemberVO> selectChatMemberAll(String roomNo) {
		SqlSession session = null;
		List<ChatMemberVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admCRoom.selectChatMemberAll", roomNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ChatRoomVO> chatRoomSearch(Map<String, String> map) {
		SqlSession session = null;
		List<ChatRoomVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admCRoom.chatRoomSearch", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ChatMemberVO> chatMemSearch(Map<String, String> map) {
		SqlSession session = null;
		List<ChatMemberVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admCRoom.chatMemSearch", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

}
