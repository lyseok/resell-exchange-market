package rem.notification.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.notification.vo.NotificationVO;
import utill.MyBatisUtil;

public class NotificationDaoImpl implements INotificationDao {
	private static INotificationDao instance;
	
	private NotificationDaoImpl() {}
	
	public static INotificationDao getInstance() {
		if(instance == null) instance = new NotificationDaoImpl();
		return instance;
	}
	
	@Override
	public List<NotificationVO> selectAllNotification(int no) {
		SqlSession session = null;
		List<NotificationVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("notification.selectAllNotification", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public int insertNotification(NotificationVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("notification.insertNotification", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int updateNotification(NotificationVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("notification.updateNotificationRead", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int seqNextVal() {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.selectOne("notification.seqNextVal");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

}
