package rem.admin.product.wishlist.dao;

import java.util.List;
import java.util.Map;

import rem.admin.product.wishlist.vo.WishlistAllVO;

public interface IWishlistAllDao {

	
	public List<WishlistAllVO> selectboardlist();
	
	public List<WishlistAllVO> searchboardlist(Map<String, String> map);
	
	
	
}
