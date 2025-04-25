package rem.admin.product.category.service;

import java.util.List;

import rem.admin.product.category.dao.CategoryDaoImpl;
import rem.admin.product.category.dao.ICategoryDao;
import rem.product.vo.CateMainVO;
import rem.product.vo.CateSubVO;

public class CategoryServiceImpl implements ICategoryService{
	private static CategoryServiceImpl service;
	
	private ICategoryDao dao;
	
	// 생성자 막기
	private CategoryServiceImpl() {
		dao = CategoryDaoImpl.getInstence();
	}
	
	public static CategoryServiceImpl getInstence() {
		if(service==null) service = new CategoryServiceImpl();
		return service;
	}

	@Override
	public List<CateMainVO> selectMainCate() {
		return dao.selectMainCate();
	}

	@Override
	public List<CateSubVO> selectSubCateAll() {
		return dao.selectSubCateAll();
	}

	@Override
	public List<CateSubVO> selectsubCate(int id) {
		return dao.selectsubCate(id);
	}

	@Override
	public int insertMainCate(String name) {
		return dao.insertMainCate(name);
	}

	@Override
	public int insertSubCate(CateSubVO vo) {
		return dao.insertSubCate(vo);
	}

	@Override
	public int updateMainCate(CateMainVO vo) {
		return dao.updateMainCate(vo);
	}

	@Override
	public int updateSubCate(CateSubVO vo) {
		return dao.updateSubCate(vo);
	}
	
	
	
}
