package rem.admin.transaction.trans.service;

import java.util.List;

import rem.admin.main.vo.AdminTransVO;

public interface ITransService {
	public List<AdminTransVO> selectAllTrans();
	public List<AdminTransVO> searchTrans(int no);
}
