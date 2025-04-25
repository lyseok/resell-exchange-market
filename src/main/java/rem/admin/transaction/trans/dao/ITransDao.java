package rem.admin.transaction.trans.dao;

import java.util.List;

import rem.admin.main.vo.AdminTransVO;

public interface ITransDao {
	public List<AdminTransVO> selectAllTrans();
	public List<AdminTransVO> searchTrans(int no);
}
