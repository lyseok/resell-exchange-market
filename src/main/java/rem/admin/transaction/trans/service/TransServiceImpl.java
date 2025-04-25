package rem.admin.transaction.trans.service;

import java.util.List;

import rem.admin.main.vo.AdminTransVO;
import rem.admin.transaction.trans.dao.ITransDao;

public class TransServiceImpl implements ITransService {
	private static ITransService instance;
	private ITransDao dao;
	
	private TransServiceImpl(ITransDao dao) {
		this.dao = dao;
	}
	
	public static ITransService getInstance(ITransDao dao) {
		if(instance == null) instance = new TransServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<AdminTransVO> selectAllTrans() {
		return dao.selectAllTrans();
	}

	@Override
	public List<AdminTransVO> searchTrans(int no) {
		return dao.searchTrans(no);
	}

}
