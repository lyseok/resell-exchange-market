package rem.admin.badge.service;

import java.util.List;
import java.util.Map;

import rem.admin.badge.vo.AchievementsVO;

public interface IAchievementsService {
	public List<AchievementsVO> selectAllAchievements();
	public List<AchievementsVO> searchAchievements(Map<String, String> map);
}
