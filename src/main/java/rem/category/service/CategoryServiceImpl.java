package rem.category.service;

import java.util.List;

import rem.category.dao.ICategoryDao;
import rem.category.vo.CategoryMainVO;
import rem.category.vo.CategorySubVO;

public class CategoryServiceImpl implements ICategoryService {
	private static ICategoryService instance;
	private ICategoryDao dao;
	
	private CategoryServiceImpl(ICategoryDao dao) {
		this.dao = dao;
	}
	
	public static ICategoryService getInstance(ICategoryDao dao) {
		if(instance == null) instance = new CategoryServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<CategoryMainVO> selectAllMainCate() {
		// TODO Auto-generated method stub
		return dao.selectAllMainCate();
	}

	@Override
	public List<CategorySubVO> selectAllSubCate(int no) {
		// TODO Auto-generated method stub
		return dao.selectAllSubCate(no);
	}

}
