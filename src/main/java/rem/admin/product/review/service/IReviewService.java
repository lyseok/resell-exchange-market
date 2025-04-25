package rem.admin.product.review.service;

import java.util.List;
import java.util.Map;

import rem.review.vo.ReviewImgVO;

public interface IReviewService {
	public List<ReviewImgVO> selectReviewList();
	
	public ReviewImgVO selectReviewModal(int txn);
	
	public int deleteReviewModal(int txn);
	
	public List<ReviewImgVO> searchReview(Map<String, String> map);
}
