package rem.notification.dao;

import java.util.List;

import rem.notification.vo.NotificationVO;

public interface INotificationDao {
	public List<NotificationVO> selectAllNotification(int no);
	public int insertNotification(NotificationVO vo);
	public int updateNotification(NotificationVO vo);
	
	public int seqNextVal();
}
