package rem.transaction.service;

import java.util.List;
import java.util.Map;

import rem.product.vo.ProductVO;
import rem.review.vo.ReviewImgVO;
import rem.review.vo.ReviewVO;
import rem.transaction.vo.ProdTransactionVO;
import rem.transaction.vo.ReviewTransactionVO;
import rem.transaction.vo.ShippingVO;
import rem.transaction.vo.TransactionImgVO;
import rem.transaction.vo.TransactionVO;

public interface ITransactionService {
	
	/**
	 * 상품 리스트 출력
	 * @return
	 */
	public List<ProductVO> getProd(int memberId);
	
	public int insertTransaction(TransactionVO vo);

	public int deleteProd(ProductVO prodVo);
	
	
	public int updateNewing(ProductVO prodVo);
	
	
	
	public List<ProdTransactionVO> getBuyProd(int memberId);
	
	
	public List<ProdTransactionVO> getSellProd(int memberId);
	
	
	public int insertTrackInfo(ShippingVO shipVo);
	
	public ReviewTransactionVO getSellProdReview(int txnNo);
	
	public ShippingVO getTrackInfo(int txnNo);
	
	public int updateConfrimProd(Map<String, Object> param);
	
	public int insertReview(ReviewImgVO rvo);
	
	
	//상품관리 분리
	public List<TransactionImgVO> getStatusAllProd(TransactionImgVO tvo);
	public List<TransactionImgVO> getStatusBuyProd(TransactionImgVO tvo);
		
		
	public List<TransactionImgVO> getStatusSellProd(TransactionImgVO tvo);
	
	
	
	public List<TransactionImgVO> getProdImg(int memberId);
	public List<TransactionImgVO> getBuyImg(int memberId);
	public List<TransactionImgVO> getSellImg(int memberId);
	
	
	
	public ReviewImgVO getReviewInfo(int txnNo);
	
	public TransactionImgVO getProdInfo(int txnNo);
	
	
	public int updateProdStatus(Map<String, Object> param);
	
	
	public List<TransactionImgVO> getProdTrStatus(TransactionImgVO tvo2);
	
	
	public int isThereReview(int txnNo);
}
