package rem.review.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.review.vo.ReviewVO;
import utill.MyBatisUtil;

public class ReviewDaoImpl implements IReviewDao {
	private static ReviewDaoImpl dao;
	private ReviewDaoImpl() {}
	public static ReviewDaoImpl getInstance() {
		if(dao==null) dao = new ReviewDaoImpl();
		return dao;
	}
	@Override
	public List<ReviewVO> getStoreReviewList(int storeId) {
		SqlSession session = null;
		List<ReviewVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreReviewList", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	
	
}
