package rem.admin.product.wishlist.service;

import java.util.List;
import java.util.Map;

import rem.admin.product.wishlist.dao.IWishlistAllDao;
import rem.admin.product.wishlist.dao.WishlistAllDaoImpl;
import rem.admin.product.wishlist.vo.WishlistAllVO;


public class WishlistAllServiceImpl implements IWishlistAllService {

	private static IWishlistAllService service;
	
	private IWishlistAllDao dao;
	
	private WishlistAllServiceImpl () {
		dao = WishlistAllDaoImpl.getinstace();
	}
	
	public static IWishlistAllService getinstance() {
		if(service == null) service = new WishlistAllServiceImpl();
		return service;
	}
	@Override
	public List<WishlistAllVO> selectboardlist() {
	
		return dao.selectboardlist();
		
		
	}

	@Override
	public List<WishlistAllVO> searchboardlist(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.searchboardlist(map);
	}

	
	
}

