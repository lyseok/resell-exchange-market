package rem.wishlist.service;

import java.util.List;

import rem.product.vo.ProductVO;
import rem.wishlist.dao.IWishlistDao;
import rem.wishlist.vo.WishlistVO;

public class WishlistServiceImpl implements IWishlistService {
	private static IWishlistService instance;
	private IWishlistDao dao;
	
	private WishlistServiceImpl(IWishlistDao dao) {
		this.dao = dao;
	}
	
	public static IWishlistService getInstance(IWishlistDao dao) {
		if(instance == null) instance = new WishlistServiceImpl(dao);
		return instance;
	}
	
	@Override
	public int countWishlist(int mem_no) {
		return dao.countWishlist(mem_no);
	}

	@Override
	public List<ProductVO> getStoreWishList(int storeId) {
		return dao.getStoreWishList(storeId);
	}

	@Override
	public int insertWish(WishlistVO vo) {
		// TODO Auto-generated method stub
		return dao.insertWish(vo);
	}

	@Override
	public int distinctWish(WishlistVO vo) {
		// TODO Auto-generated method stub
		return dao.distinctWish(vo);
	}

	@Override
	public int deleteWish(WishlistVO vo) {
		// TODO Auto-generated method stub
		return dao.deleteWish(vo);
	}

	@Override
	public int countProdWish(int prod_no) {
		// TODO Auto-generated method stub
		return dao.countProdWish(prod_no);
	}

}
