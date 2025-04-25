package rem.notification.service;

import java.util.List;

import rem.notification.dao.INotificationDao;
import rem.notification.vo.NotificationVO;

public class NotificationServiceImpl implements INotificationService {
	private static INotificationService instance;
	private INotificationDao dao;
	
	private NotificationServiceImpl(INotificationDao dao) {
		this.dao = dao;
	}
	
	public static INotificationService getInstance(INotificationDao dao) {
		if(instance == null) instance = new NotificationServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<NotificationVO> selectAllNotification(int no) {
		return dao.selectAllNotification(no);
	}

	@Override
	public int insertNotification(NotificationVO vo) {
		vo.setNotif_id(seqNextVal());
		return dao.insertNotification(vo);
	}

	@Override
	public int updateNotification(NotificationVO vo) {
		return dao.updateNotification(vo);
	}

	@Override
	public int seqNextVal() {
		return dao.seqNextVal();
	}

}
