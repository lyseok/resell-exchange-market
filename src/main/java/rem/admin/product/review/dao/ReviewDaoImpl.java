package rem.admin.product.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.review.vo.ReviewImgVO;
import utill.MyBatisUtil;

public class ReviewDaoImpl implements IReviewDao {
	private static ReviewDaoImpl dao;
	
	private ReviewDaoImpl () {}
	
	public static ReviewDaoImpl getInstence(){
		if(dao==null) dao = new ReviewDaoImpl();
		return dao;
	}
	

	@Override
	public List<ReviewImgVO> selectReviewList() {
		SqlSession session = null;
		List<ReviewImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admrv.selectReviewList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public ReviewImgVO selectReviewModal(int txn) {
		SqlSession session = null;
		ReviewImgVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("admrv.selectReviewModal", txn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return vo;
	}

	@Override
	public int deleteReviewModal(int txn) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.delete("admrv.deleteReviewModal", txn);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<ReviewImgVO> searchReview(Map<String, String> map) {
		SqlSession session = null;
		List<ReviewImgVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("admrv.searchReview", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

}
