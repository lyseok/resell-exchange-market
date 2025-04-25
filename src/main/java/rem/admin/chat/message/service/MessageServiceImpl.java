package rem.admin.chat.message.service;

import java.util.List;
import java.util.Map;

import rem.admin.chat.message.dao.IMessageDao;
import rem.admin.chat.message.dao.MessageDaoImpl;
import rem.chat.vo.MessageVO;

public class MessageServiceImpl implements IMessageService {
	private static MessageServiceImpl service;
	// dao 가져오기
	private IMessageDao dao;
	
	// 생성자 막기
	private MessageServiceImpl() {
		dao = MessageDaoImpl.getinstance();
	}

	public static MessageServiceImpl getInstance() {
		if(service==null) service = new MessageServiceImpl();
		return service;
	}
	
	@Override
	public List<MessageVO> selectMessageList() {
		return dao.selectMessageList();
	}

	@Override
	public List<MessageVO> searchMessage(Map<String, String> map) {
		return dao.searchMessage(map);
	}

	@Override
	public List<MessageVO> searchMessageCont(String Cont) {
		return dao.searchMessageCont(Cont);
	}

}
