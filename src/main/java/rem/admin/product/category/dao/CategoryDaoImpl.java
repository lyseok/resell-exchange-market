package rem.admin.product.category.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.product.vo.CateMainVO;
import rem.product.vo.CateSubVO;
import utill.MyBatisUtil;

public class CategoryDaoImpl implements ICategoryDao {
	private static CategoryDaoImpl dao;
	
	// 생성자 막기
	private CategoryDaoImpl (){}
	
	public static CategoryDaoImpl getInstence() {
		if(dao == null) dao = new CategoryDaoImpl();
		return dao;
	}

	@Override
	public List<CateMainVO> selectMainCate() {
		SqlSession session = null;
		List<CateMainVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admcate.selectMainCate");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<CateSubVO> selectSubCateAll() {
		SqlSession session = null;
		List<CateSubVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admcate.selectSubCateAll");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<CateSubVO> selectsubCate(int id) {
		SqlSession session = null;
		List<CateSubVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admcate.selectSubCate", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public int insertMainCate(String name) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("admcate.insertMainCate", name);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int insertSubCate(CateSubVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("admcate.insertSubCate", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateMainCate(CateMainVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("admcate.updateMainCate", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateSubCate(CateSubVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("admcate.updateSubCate", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	

}
