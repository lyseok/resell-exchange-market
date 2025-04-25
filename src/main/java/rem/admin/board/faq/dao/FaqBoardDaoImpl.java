package rem.admin.board.faq.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.faq.vo.FAQBoardVO;
import utill.MyBatisUtil;

public class FaqBoardDaoImpl implements IFaqBoardDao {

	private static IFaqBoardDao dao;
	
	private FaqBoardDaoImpl() {}
	
	public static IFaqBoardDao getinstance() {
		if(dao == null) dao = new FaqBoardDaoImpl();
		return dao;
	}
	
	@Override
	public List<FAQBoardVO> selectFaqBoardList() {
		SqlSession session = null;
		List<FAQBoardVO> fbvol = null;
		try {
			session = MyBatisUtil.getSqlSession();
			  fbvol = session.selectList("FAQBoard.selectAllFAQBoard"); 	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}return fbvol;
		 
	}

	@Override
	public FAQBoardVO selectFaqView(int faq_no) {
		SqlSession session = null;
		FAQBoardVO fbvo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			fbvo = session.selectOne("FAQBoard.selectFAQBoard", faq_no);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}return fbvo;
	}

	@Override
	public int insertFaq(FAQBoardVO FAQBoardVO) {
		SqlSession session = null;
		int fbcnt = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			fbcnt = session.insert("FAQBoard.insertFAQBoard", FAQBoardVO);
			if(fbcnt >0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)session.close();
		}
		return fbcnt;
	}

	@Override
	public int updateFaq(FAQBoardVO FAQBoardVO) {
		SqlSession session = null;
		int fbcnt = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			fbcnt = session.update("FAQBoard.updateFAQBoard", FAQBoardVO);
			if(fbcnt >0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fbcnt;
	}

	@Override
	public int deleteFaq(int faq_no) {
		SqlSession session = null;
		int fbcnt = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			fbcnt = session.delete("FAQBoard.deleteFAQBoard", faq_no);
			if(fbcnt >0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}return fbcnt;
	}

	@Override
	public List<FAQBoardVO> selectByType(String faq_type) {
		SqlSession session = null;
		List<FAQBoardVO> fbvol = null;
		try {
			session = MyBatisUtil.getSqlSession();
			fbvol = session.selectList("FAQBoard.selectedFAQBoard", faq_type);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return fbvol;
	}
	
	
	

}
