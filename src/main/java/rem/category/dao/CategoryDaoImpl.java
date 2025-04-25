package rem.category.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.category.vo.CategoryMainVO;
import rem.category.vo.CategorySubVO;
import utill.MyBatisUtil;

public class CategoryDaoImpl implements ICategoryDao {
	private static ICategoryDao instance;
	
	private CategoryDaoImpl() {}
	
	public static ICategoryDao getInstance() {
		if(instance == null) instance = new CategoryDaoImpl();
		return instance;
	}
	
	@Override
	public List<CategoryMainVO> selectAllMainCate() {
		SqlSession session = null;
		List<CategoryMainVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("category.selectAllMainCate");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<CategorySubVO> selectAllSubCate(int no) {
		SqlSession session = null;
		List<CategorySubVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("category.selectAllSubCate");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

}
