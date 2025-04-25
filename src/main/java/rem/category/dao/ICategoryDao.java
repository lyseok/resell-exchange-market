package rem.category.dao;

import java.util.List;

import rem.category.vo.CategoryMainVO;
import rem.category.vo.CategorySubVO;

public interface ICategoryDao {
	public List<CategoryMainVO> selectAllMainCate();
	public List<CategorySubVO> selectAllSubCate(int no);
}
