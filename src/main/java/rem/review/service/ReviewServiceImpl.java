package rem.review.service;

import java.util.List;

import rem.review.dao.IReviewDao;
import rem.review.dao.ReviewDaoImpl;
import rem.review.vo.ReviewVO;

public class ReviewServiceImpl implements IReviewService {
	private IReviewDao dao;
	private static ReviewServiceImpl service;
	private ReviewServiceImpl() {
		dao = ReviewDaoImpl.getInstance();
	}
	public static ReviewServiceImpl getInstance() {
		if(service==null) service = new ReviewServiceImpl();
		return service;
	}
	@Override
	public List<ReviewVO> getStoreReviewList(int storeId) {
		return dao.getStoreReviewList(storeId);
	}

	
	
}
