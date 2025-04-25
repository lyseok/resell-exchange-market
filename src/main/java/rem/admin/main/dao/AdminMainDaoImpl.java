package rem.admin.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.admin.main.vo.AdminTransVO;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;
import utill.MyBatisUtil;

public class AdminMainDaoImpl implements IAdminMainDao {
	private static IAdminMainDao instance;
	
	private AdminMainDaoImpl() {}
	
	public static IAdminMainDao getInstace() {
		if(instance == null) instance = new AdminMainDaoImpl();
		return instance;
	}
	
	@Override
	public List<MemberVO> selectAdminMainMember() {
		SqlSession session = null;
		List<MemberVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("member.selectAdminMainMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<ProductVO> selectAdminMainProduct() {
		SqlSession session = null;
		List<ProductVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.selectAdminMainProduct");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<AdminTransVO> selectAdminMainTransact() {
		SqlSession session = null;
		List<AdminTransVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.selectAdminMainTransact");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

}
