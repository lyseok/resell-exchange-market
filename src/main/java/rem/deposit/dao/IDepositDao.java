package rem.deposit.dao;

import java.util.List;

import rem.deposit.vo.DepositVO;

public interface IDepositDao {
	public List<DepositVO> selectAllDeposit(int no);
	public DepositVO selectDeposit(int no);
	public int insertDeposit(DepositVO vo);
}
