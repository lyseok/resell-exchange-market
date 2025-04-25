package rem.admin.product.category.service;

import java.util.List;

import rem.product.vo.CateMainVO;
import rem.product.vo.CateSubVO;

public interface ICategoryService {
	public List<CateMainVO> selectMainCate();
	
	public List<CateSubVO> selectSubCateAll();

	public List<CateSubVO> selectsubCate(int id);

	public int insertMainCate(String name);
	
	public int insertSubCate(CateSubVO vo);

	public int updateMainCate(CateMainVO vo);

	public int updateSubCate(CateSubVO vo);
}
