package rem.transaction.service;

import java.util.List;
import java.util.Map;

import rem.product.vo.ProductVO;
import rem.review.vo.ReviewImgVO;
import rem.review.vo.ReviewVO;
import rem.transaction.dao.ITransactionDao;
import rem.transaction.dao.TransactionDaoImpl;
import rem.transaction.vo.ProdTransactionVO;
import rem.transaction.vo.ReviewTransactionVO;
import rem.transaction.vo.ShippingVO;
import rem.transaction.vo.TransactionImgVO;
import rem.transaction.vo.TransactionVO;

public class TransactionServiceImpl implements ITransactionService{
	
	private static TransactionServiceImpl service;
	
	private ITransactionDao dao;
	

	private TransactionServiceImpl() {
		dao = TransactionDaoImpl.getInstance();
	}
	
	public static TransactionServiceImpl getInstance(){
		if (service == null) {
			service = new TransactionServiceImpl();
		}
		return service;
	}

	@Override
	public List<ProductVO> getProd(int memberId) {
		return dao.getProd(memberId);
	}

	@Override
	public int deleteProd(ProductVO prodVo) {
		
		return dao.deleteProd(prodVo);
	}

	@Override
	public int updateNewing(ProductVO prodVo) {
		return dao.updateNewing(prodVo); 
	}

	@Override
	public List<ProdTransactionVO> getBuyProd(int memberId) {
		return dao.getBuyProd(memberId);
	}

	@Override
	public List<ProdTransactionVO> getSellProd(int memberId) {
		return dao.getSellProd(memberId);
	}

	@Override
	public int insertTrackInfo(ShippingVO shipVo) {
		return dao.insertTrackInfo(shipVo);
	}

	@Override
	public ReviewTransactionVO getSellProdReview(int txnNo) {
		return dao.getSellProdReview(txnNo);
	}

	@Override
	public ShippingVO getTrackInfo(int txnNo) {
		return dao.getTrackInfo(txnNo);
	}

	@Override
	public int updateConfrimProd(Map<String, Object> param) {
		return dao.updateConfrimProd(param);
	}

	@Override
	public int insertReview(ReviewImgVO rvo) {
		return dao.insertReview(rvo);
	}

	@Override
	public int insertTransaction(TransactionVO vo) {
		// TODO Auto-generated method stub
		return dao.insertTransaction(vo);
	}

	@Override
	public List<TransactionImgVO> getStatusAllProd(TransactionImgVO tvo) {
		return dao.getStatusAllProd(tvo);
	}
	
	@Override
	public List<TransactionImgVO> getStatusBuyProd(TransactionImgVO tvo) {
		return dao.getStatusBuyProd(tvo);
	}

	@Override
	public List<TransactionImgVO> getStatusSellProd(TransactionImgVO tvo) {
		return dao.getStatusSellProd(tvo);
	}

	@Override
	public List<TransactionImgVO> getProdImg(int memberId) {
		return dao.getProdImg(memberId);
	}

	@Override
	public List<TransactionImgVO> getBuyImg(int memberId) {
		return dao.getBuyImg(memberId);
	}

	@Override
	public List<TransactionImgVO> getSellImg(int memberId) {
		return dao.getSellImg(memberId);
	}

	@Override
	public ReviewImgVO getReviewInfo(int txnNo) {
		return dao.getReviewInfo(txnNo);
	}

	@Override
	public TransactionImgVO getProdInfo(int txnNo) {
		return dao.getProdInfo(txnNo);
	}

	@Override
	public int updateProdStatus(Map<String, Object> param) {
		return dao.updateProdStatus(param);
	}

	@Override
	public List<TransactionImgVO> getProdTrStatus(TransactionImgVO tvo2) {
		return dao.getProdTrStatus(tvo2);
	}

	@Override
	public int isThereReview(int txnNo) {
		return dao.isThereReview(txnNo);
	}

	
	
	
	
	
	
}
