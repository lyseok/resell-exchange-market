package rem.deposit.service;

import java.util.List;

import rem.deposit.vo.DepositVO;

public interface IDepositService {
	public List<DepositVO> selectAllDeposit(int no);
	public DepositVO selectDeposit(int no);
	public int insertDeposit(DepositVO vo);
}
