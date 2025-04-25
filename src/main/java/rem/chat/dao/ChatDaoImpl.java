package rem.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.chat.vo.ChatMemberVO;
import rem.chat.vo.ChatRoomVO;
import rem.chat.vo.ChatVO;
import rem.chat.vo.MessageVO;
import utill.MyBatisUtil;

public class ChatDaoImpl implements IChatDao {
	private static ChatDaoImpl dao;
	
	private ChatDaoImpl() {};
	
	public static ChatDaoImpl getInstance() {
		if(dao==null) dao = new ChatDaoImpl();
		return dao;
	}

	@Override
	public List<ChatVO> getAllChatuser(int mem_no) {
		SqlSession session = null;
		List<ChatVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("chat.getAllChatuser", mem_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ChatVO> getAllChatroom(int mem_no) {
		SqlSession session = null;
		List<ChatVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("chat.getAllChatroom", mem_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<MessageVO> getMessage(int chat_room_no) {
		SqlSession session = null;
		List<MessageVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			list = session.selectList("chat.getMessage", chat_room_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public int insertChatroom(ChatRoomVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.insert("chat.insertChatroom", vo);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

	@Override
	public int insertChatmember(ChatVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.insert("chat.insertChatmember", vo);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

	@Override
	public int countChatroom(Map<String, Object> map) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.selectOne("chat.countChatroom", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int sendMessage(MessageVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.insert("chat.sendMessage", vo);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

}
