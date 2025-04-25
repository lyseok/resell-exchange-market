package rem.admin.chat.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.chat.vo.MessageVO;
import utill.MyBatisUtil;

public class MessageDaoImpl implements IMessageDao {
	private static MessageDaoImpl dao;
	
	private MessageDaoImpl() {}
	
	// 클래스 반환값 메서드 생성
	public static MessageDaoImpl getinstance() {
		if(dao==null) dao = new MessageDaoImpl();
		return dao;
	}
	
	@Override
	/**
	 * 관리자 페이지 메세지 리스트 전체 출력
	 */
	public List<MessageVO> selectMessageList() {
		SqlSession session = null;
		List<MessageVO> msgList = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			msgList = session.selectList("message.selectMessageList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return msgList;
	}

	@Override
	public List<MessageVO> searchMessage(Map<String, String> map) {
		SqlSession session = null;
		List<MessageVO> msgList = null;
		try {
			session = MyBatisUtil.getSqlSession();
			msgList = session.selectList("message.searchMessage", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return msgList;
	}

	@Override
	public List<MessageVO> searchMessageCont(String Cont) {
		SqlSession session = null;
		List<MessageVO> msgList = null;
		try {
			session = MyBatisUtil.getSqlSession();
			msgList = session.selectList("message.searchMessageCont", Cont);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return msgList;
	}

}
