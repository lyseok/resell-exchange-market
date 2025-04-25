package rem.store.service;

import java.util.List;
import java.util.Map;

import rem.login.vo.MemberVO;
import rem.product.vo.ProdImgVO;
import rem.review.vo.ReviewImgVO;

public interface IStoreService {
	public int getProgSell(int storeId);
	public int getCountAllProducts(int storeId);
	public int getCountSoldoutProducts(int storeId);
	public int updateMypageProfile(Map<String, Object> editedMap);
	public MemberVO getStoreInfoByProd(int prod_no);
	public MemberVO getStoreInfoByMem(int mem_no);
	public List<ProdImgVO> getStoreProdList(int storeId);
	public List<ProdImgVO> getStoreSoldoutList(int storeId);
	public List<ProdImgVO> getStoreWishlist(int storeId);
	public List<ReviewImgVO> getStoreReviewList(int storeId);
	public double storeRatingAvg(int storeId);
}
