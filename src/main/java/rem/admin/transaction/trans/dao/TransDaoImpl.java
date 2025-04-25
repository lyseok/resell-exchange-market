package rem.admin.transaction.trans.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.admin.main.vo.AdminTransVO;
import utill.MyBatisUtil;

public class TransDaoImpl implements ITransDao {
	private static ITransDao instance;
	
	private TransDaoImpl() {}
	
	public static ITransDao getInstance() {
		if(instance == null) instance = new TransDaoImpl();
		return instance;
	}
	@Override
	public List<AdminTransVO> selectAllTrans() {
		SqlSession session = null;
		List<AdminTransVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.selectAllAdminMainTransact");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<AdminTransVO> searchTrans(int no) {
		SqlSession session = null;
		List<AdminTransVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.searchAdminMainTransact", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

}
