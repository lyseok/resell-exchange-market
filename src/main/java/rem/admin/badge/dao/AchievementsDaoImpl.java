package rem.admin.badge.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.badge.vo.AchievementsVO;
import utill.MyBatisUtil;

public class AchievementsDaoImpl implements IAchievementsDao {
	private static IAchievementsDao instance;
	
	private AchievementsDaoImpl() {}
	
	public static IAchievementsDao getInstance() {
		if(instance == null) instance = new AchievementsDaoImpl();
		return instance;
	}
	
	@Override
	public List<AchievementsVO> selectAllAchievements() {
		SqlSession session = null;
		List<AchievementsVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("achievements.selectAllAchievements");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<AchievementsVO> searchAchievements(Map<String, String> map) {
		SqlSession session = null;
		List<AchievementsVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("achievements.searchAchievements", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

}
