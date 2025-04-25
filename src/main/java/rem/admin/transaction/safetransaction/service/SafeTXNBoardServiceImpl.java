package rem.admin.transaction.safetransaction.service;

import java.util.List;
import java.util.Map;

import rem.admin.transaction.safetransaction.dao.ISafeTXNBoardDao;
import rem.admin.transaction.safetransaction.dao.SafeTXNBoardDaoImpl;
import rem.admin.transaction.safetransaction.vo.SafeTXNVO;

public class SafeTXNBoardServiceImpl implements ISafeTXNBoardService {

	private static ISafeTXNBoardService service;
	
	private ISafeTXNBoardDao dao;
	
	private SafeTXNBoardServiceImpl() {
		dao = SafeTXNBoardDaoImpl.getinstance();
	}
	
	public static ISafeTXNBoardService getinstance () {
		if(service == null) service = new SafeTXNBoardServiceImpl();
		return service;
	}
	
	
	
	@Override
	public List<SafeTXNVO> selectboardlist() {
		
		return dao.selectboardlist();
	}

	@Override
	public List<SafeTXNVO> searchboardlist(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.searchboardlist(map);
	}

	@Override
	public SafeTXNVO searchmodal(int safeNo) {
		// TODO Auto-generated method stub
		return dao.searchmodal(safeNo);
	}

	
	

}
