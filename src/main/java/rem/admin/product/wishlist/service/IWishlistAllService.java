package rem.admin.product.wishlist.service;

import java.util.List;
import java.util.Map;

import rem.admin.product.wishlist.vo.WishlistAllVO;



public interface IWishlistAllService {

	
	public List<WishlistAllVO> selectboardlist();
	
	public List<WishlistAllVO> searchboardlist(Map<String, String> map);
	
}
