package rem.admin.badge.dao;

import java.util.List;
import java.util.Map;

import rem.admin.badge.vo.AchievementsVO;

public interface IAchievementsDao {
	public List<AchievementsVO> selectAllAchievements();
	public List<AchievementsVO> searchAchievements(Map<String, String> map);
	
}
