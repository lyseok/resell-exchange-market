package rem.admin.product.view.service;

import java.util.List;
import java.util.Map;

import rem.product.vo.ViewCountVO;

public interface IViewService {
	
	public List<ViewCountVO> selectboardlist();
	
	public List<ViewCountVO> searchboardlist(Map<String, String> map);
}
