package rem.admin.chat.message.dao;

import java.util.List;
import java.util.Map;

import rem.chat.vo.MessageVO;

public interface IMessageDao {
	public List<MessageVO> selectMessageList();
	
	public List<MessageVO> searchMessage(Map<String, String> map);
	
	public List<MessageVO> searchMessageCont(String Cont);
}
