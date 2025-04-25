package rem.admin.product.view.service;

import java.util.List;
import java.util.Map;

import rem.admin.product.view.dao.IViewDao;
import rem.admin.product.view.dao.ViewDaoImpl;
import rem.product.vo.ViewCountVO;

public class ViewServiceImpl implements IViewService {

	private static IViewService service;
	
	private IViewDao dao;
	
	private ViewServiceImpl() {
		dao = ViewDaoImpl.getinstace();
	}
	public static IViewService getinstance() {
		if(service == null) service = new ViewServiceImpl();
		return service;
	}
	
	@Override
	public List<ViewCountVO> selectboardlist() {
		// TODO Auto-generated method stub
		return dao.selectboardlist();
	}

	@Override
	public List<ViewCountVO> searchboardlist(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.searchboardlist(map);
	}

}
