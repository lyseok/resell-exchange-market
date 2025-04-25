package rem.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.faq.vo.FAQBoardVO;
import rem.admin.board.notice.vo.NoticeBoardVO;
import rem.admin.board.qna.vo.QnaBoardVO;
import rem.admin.board.qna.vo.QnaSetVO;
import rem.admin.board.report.vo.ReportBoardVO;
import rem.admin.board.report.vo.ReportSetVO;
import rem.product.vo.ProdImgVO;
import utill.MyBatisUtil;

public class SearchDaoImpl implements ISearchDao {
	private static ISearchDao instance;
	
	private SearchDaoImpl() {}
	
	public static ISearchDao getInstance() {
		if(instance == null) instance = new SearchDaoImpl();
		return instance;
	}
	
	@Override
	public List<ProdImgVO> selectSearchProduct(String searchText) {
		SqlSession session = null;
		List<ProdImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.selectSearchProduct", searchText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public List<NoticeBoardVO> searchNoticeBoard(String searchText) {
		SqlSession session = null;
		List<NoticeBoardVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.searchNoticeBoard", searchText);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public List<QnaBoardVO> getQnaBoard() {
		SqlSession session = null;
		List<QnaBoardVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.getQnaBoardList");
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public QnaSetVO getQnaBoard(Map<String, Integer> map) {
		SqlSession session = null;
		QnaSetVO vo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("search.getQnaBoardOne", map);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}

	@Override
	public List<QnaBoardVO> searchQnaBoard(Map<String, Object> map) {
		SqlSession session = null;
		List<QnaBoardVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.searchQnaBoard", map);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public QnaSetVO getQnaComments(int qna_no) {
		SqlSession session = null;
		QnaSetVO vo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("search.getQnaComments", qna_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}

	@Override
	public List<FAQBoardVO> getFAQBoardList() {
		SqlSession session = null;
		List<FAQBoardVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.getFAQBoardList");
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public List<ReportBoardVO> searchReportBoard(String searchText) {
		SqlSession session = null;
		List<ReportBoardVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.searchReportBoard", searchText);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public ReportSetVO getReportBoardOne(int rptNo) {
		SqlSession session = null;
		ReportSetVO vo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("search.getReportBoardOne", rptNo);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}

	@Override
	public ReportSetVO getReportComments(int rpt_mem_no) {
		SqlSession session = null;
		ReportSetVO vo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("search.getReportComments", rpt_mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}

	@Override
	public  List<ReportSetVO> getReportBoardList() {
		SqlSession session = null;
		List<ReportSetVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("search.getReportBoardList");
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		
		return list;
	}

}
