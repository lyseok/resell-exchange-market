package rem.admin.badge.service;

import java.util.List;
import java.util.Map;

import rem.admin.badge.dao.IAchievementsDao;
import rem.admin.badge.vo.AchievementsVO;

public class AchievementsServiceImpl implements IAchievementsService {
	private static IAchievementsService instance;
	private IAchievementsDao dao;
	
	private AchievementsServiceImpl(IAchievementsDao dao) {
		this.dao = dao;
	}
	
	public static IAchievementsService getInstance(IAchievementsDao dao) {
		if(instance == null) instance = new AchievementsServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<AchievementsVO> selectAllAchievements() {
		return dao.selectAllAchievements();
	}

	@Override
	public List<AchievementsVO> searchAchievements(Map<String, String> map) {
		return dao.searchAchievements(map);
	}

}
