package rem.store.service;

import java.util.List;
import java.util.Map;

import rem.login.vo.MemberVO;
import rem.product.vo.ProdImgVO;
import rem.review.vo.ReviewImgVO;
import rem.store.dao.IStoreDao;
import rem.store.dao.StoreDaoImpl;

public class StoreServiceImpl implements IStoreService {
	private static IStoreDao dao;
	private static StoreServiceImpl service;
	private StoreServiceImpl() {
		dao = StoreDaoImpl.getInstance();
	}
	public static StoreServiceImpl getInstance() {
		if(service==null) service = new StoreServiceImpl();
		return service;
	}
	@Override
	public int getProgSell(int storeId) {
		return dao.getProgSell(storeId);
	}
	@Override
	public int getCountAllProducts(int storeId) {
		return dao.getCountAllProducts(storeId);
	}
	@Override
	public int getCountSoldoutProducts(int storeId) {
		return dao.getCountSoldoutProducts(storeId);
	}
	@Override
	public int updateMypageProfile(Map<String, Object> editedMap) {
		return dao.updateMypageProfile(editedMap);
	}
	@Override
	public MemberVO getStoreInfoByProd(int prod_no) {
		return dao.getStoreInfoByProd(prod_no);
	}
	@Override
	public MemberVO getStoreInfoByMem(int mem_no) {
		return dao.getStoreInfoByMem(mem_no);
	}
	@Override
	public List<ProdImgVO> getStoreProdList(int storeId) {
		return dao.getStoreProdList(storeId);
	}
	@Override
	public List<ProdImgVO> getStoreSoldoutList(int storeId) {
		return dao.getStoreSoldoutList(storeId);
	}
	@Override
	public List<ProdImgVO> getStoreWishlist(int storeId) {
		return dao.getStoreWishlist(storeId);
	}
	@Override
	public List<ReviewImgVO> getStoreReviewList(int storeId) {
		return dao.getStoreReviewList(storeId);
	}
	@Override
	public double storeRatingAvg(int storeId) {
		return dao.storeRatingAvg(storeId);
	}
	
	
}
