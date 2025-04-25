package rem.wishlist.service;

import java.util.List;

import rem.product.vo.ProductVO;
import rem.wishlist.vo.WishlistVO;

public interface IWishlistService {
	public int countWishlist(int mem_no);
	public List<ProductVO> getStoreWishList(int storeId);
	public int insertWish(WishlistVO vo);
	public int distinctWish(WishlistVO vo);
	public int deleteWish(WishlistVO vo);
	public int countProdWish(int prod_no);
}
