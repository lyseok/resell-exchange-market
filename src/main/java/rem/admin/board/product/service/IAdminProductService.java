package rem.admin.board.product.service;

import java.util.List;
import java.util.Map;

import rem.product.vo.ProductVO;

public interface IAdminProductService {
	public List<ProductVO> selectAdminProductList();

	public List<ProductVO> searchAdminProduct(Map<String, String> map);
	
}
