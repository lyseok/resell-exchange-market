package rem.admin.board.product.service;

import java.util.List;
import java.util.Map;

import rem.admin.board.product.dao.AdminProductDaoImpl;
import rem.admin.board.product.dao.IAdminProductDao;
import rem.product.vo.ProductVO;

public class AdminProductServiceImpl implements IAdminProductService {
	private static AdminProductServiceImpl service;
	
	private IAdminProductDao dao;
	
	private AdminProductServiceImpl() {
		dao = AdminProductDaoImpl.getInstence();
	}
	
	public static AdminProductServiceImpl getInstence() {
		if(service==null) service = new AdminProductServiceImpl();
		return service;
	}
	
	@Override
	public List<ProductVO> selectAdminProductList() {
		return dao.selectAdminProductList();
	}

	@Override
	public List<ProductVO> searchAdminProduct(Map<String, String> map) {
		return dao.searchAdminProduct(map);
	}

}
