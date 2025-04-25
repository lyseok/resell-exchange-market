package rem.admin.transaction.safetransaction.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.transaction.safetransaction.vo.SafeTXNVO;
import utill.MyBatisUtil;

public class SafeTXNBoardDaoImpl implements ISafeTXNBoardDao {

	private static ISafeTXNBoardDao dao;
	
	private SafeTXNBoardDaoImpl() {}
	
	public static ISafeTXNBoardDao getinstance() {
		if(dao == null) dao = new SafeTXNBoardDaoImpl();
		return dao;
	}
	
	
	@Override
	public List<SafeTXNVO> selectboardlist() {
		SqlSession session = null;
		List<SafeTXNVO> lstv = null;
		try {
			session = MyBatisUtil.getSqlSession();
			lstv = session.selectList("txnlistall.gettxnlistall");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}return lstv;
		
	}

	@Override
	public List<SafeTXNVO> searchboardlist(Map<String, String> map) {
		SqlSession session = null;
		List<SafeTXNVO> lstv = null;
		try {
			session = MyBatisUtil.getSqlSession();
			lstv = session.selectList("txnlistall.searchtxnlist", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return lstv;
	}

	@Override
	public SafeTXNVO searchmodal(int safeNo) {
		SqlSession session = null;
		SafeTXNVO cntstv = null;
		try {
			session = MyBatisUtil.getSqlSession();
			cntstv = session.selectOne("txnlistall.txnlistmodal", safeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return cntstv;
	}

	

	
	
	
}
