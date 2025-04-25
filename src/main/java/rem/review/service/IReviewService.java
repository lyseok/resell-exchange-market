package rem.review.service;

import java.util.List;

import rem.review.vo.ReviewVO;

public interface IReviewService {
	public List<ReviewVO> getStoreReviewList(int storeId);
}
