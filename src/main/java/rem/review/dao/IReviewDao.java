package rem.review.dao;

import java.util.List;

import rem.review.vo.ReviewVO;

public interface IReviewDao {
	public List<ReviewVO> getStoreReviewList(int storeId);
}
