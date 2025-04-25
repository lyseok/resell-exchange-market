package rem.admin.board.product.dao;

import java.util.List;
import java.util.Map;

import rem.product.vo.ProductVO;

public interface IAdminProductDao {
	public List<ProductVO> selectAdminProductList();
	
	public List<ProductVO> searchAdminProduct(Map<String, String> map);
}
