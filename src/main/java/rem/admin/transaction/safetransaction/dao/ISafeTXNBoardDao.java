package rem.admin.transaction.safetransaction.dao;

import java.util.List;
import java.util.Map;

import rem.admin.transaction.safetransaction.vo.SafeTXNVO;

public interface ISafeTXNBoardDao {

	public List<SafeTXNVO> selectboardlist();
	
	public List<SafeTXNVO> searchboardlist(Map<String, String> map);
	
	public SafeTXNVO searchmodal(int safeNo);
}
