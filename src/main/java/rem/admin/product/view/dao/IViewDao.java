package rem.admin.product.view.dao;

import java.util.List;
import java.util.Map;


import rem.product.vo.ViewCountVO;

public interface IViewDao {
	
	public List<ViewCountVO> selectboardlist();
	
	public List<ViewCountVO> searchboardlist(Map<String, String> map);
}
