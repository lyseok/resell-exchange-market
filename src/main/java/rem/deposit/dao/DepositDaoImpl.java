package rem.deposit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.deposit.vo.DepositVO;
import utill.MyBatisUtil;

public class DepositDaoImpl implements IDepositDao {
	private static IDepositDao instance;
	
	private DepositDaoImpl() {}
	
	public static IDepositDao getInstance() {
		if(instance == null) instance = new DepositDaoImpl();
		return instance;
	}
	
	@Override
	public List<DepositVO> selectAllDeposit(int no) {
		SqlSession session = null;
		List<DepositVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("deposit.selectAllDeposit", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public DepositVO selectDeposit(int no) {
		SqlSession session = null;
		DepositVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("deposit.selectDeposit", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return vo;
	}

	@Override
	public int insertDeposit(DepositVO vo) {
		SqlSession session = null;;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("deposit.insertDeposit", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

}
