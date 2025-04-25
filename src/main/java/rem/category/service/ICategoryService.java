package rem.category.service;

import java.util.List;

import rem.category.vo.CategoryMainVO;
import rem.category.vo.CategorySubVO;

public interface ICategoryService {
	public List<CategoryMainVO> selectAllMainCate();
	public List<CategorySubVO> selectAllSubCate(int no);
}
