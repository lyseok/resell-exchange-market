package rem.deposit.service;

import java.util.List;

import rem.deposit.dao.IDepositDao;
import rem.deposit.vo.DepositVO;

public class DepositServiceImpl implements IDepositService {
	private static IDepositService instance;
	private IDepositDao dao;
	
	private DepositServiceImpl(IDepositDao dao) {
		this.dao = dao;
	}
	
	public static IDepositService getInstance(IDepositDao dao) {
		if(instance == null) instance = new DepositServiceImpl(dao);
		return instance;
	}

	
	@Override
	public List<DepositVO> selectAllDeposit(int no) {
		return dao.selectAllDeposit(no);
	}

	@Override
	public DepositVO selectDeposit(int no) {
		return dao.selectDeposit(no);
	}

	@Override
	public int insertDeposit(DepositVO vo) {
		return dao.insertDeposit(vo);
	}

}
