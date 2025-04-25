package rem.search.service;

import java.util.List;
import java.util.Map;

import rem.admin.board.faq.vo.FAQBoardVO;
import rem.admin.board.notice.vo.NoticeBoardVO;
import rem.admin.board.qna.vo.QnaBoardVO;
import rem.admin.board.qna.vo.QnaSetVO;
import rem.admin.board.report.vo.ReportBoardVO;
import rem.admin.board.report.vo.ReportSetVO;
import rem.product.vo.ProdImgVO;
import rem.search.dao.ISearchDao;

public class SearchServiceImpl implements ISearchService {
	private static ISearchService instance;
	private ISearchDao dao;
	
	private SearchServiceImpl(ISearchDao dao) {
		this.dao = dao;
	}
	
	public static ISearchService getInstance(ISearchDao dao) {
		if(instance == null) instance = new SearchServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<ProdImgVO> selectSearchProduct(String searchText) {
		return dao.selectSearchProduct(searchText);
	}

	@Override
	public List<NoticeBoardVO> searchNoticeBoard(String searchText) {
		return dao.searchNoticeBoard(searchText);
	}

	@Override
	public List<QnaBoardVO> getQnaBoard() {
		return dao.getQnaBoard();
	}

	@Override
	public QnaSetVO getQnaBoard(Map<String, Integer> map) {
		return dao.getQnaBoard(map);
	}

	@Override
	public List<QnaBoardVO> searchQnaBoard(Map<String, Object> map) {
		return dao.searchQnaBoard(map);
	}

	@Override
	public QnaSetVO getQnaComments(int qna_no) {
		return dao.getQnaComments(qna_no);
	}

	@Override
	public List<FAQBoardVO> getFAQBoardList() {
		return dao.getFAQBoardList();
	}

	@Override
	public List<ReportBoardVO> searchReportBoard(String searchText) {
		return dao.searchReportBoard(searchText);
	}

	@Override
	public ReportSetVO getReportBoardOne(int rptNo) {
		return dao.getReportBoardOne(rptNo);
	}

	@Override
	public ReportSetVO getReportComments(int rpt_mem_no) {
		return dao.getReportComments(rpt_mem_no);
	}

	@Override
	public List<ReportSetVO> getReportBoardList() {
		return dao.getReportBoardList();
	}


}
