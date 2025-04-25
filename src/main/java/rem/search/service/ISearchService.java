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

public interface ISearchService {
	public List<ProdImgVO> selectSearchProduct(String searchText);
	public List<NoticeBoardVO> searchNoticeBoard(String searchText);
	public List<QnaBoardVO> getQnaBoard();
	public QnaSetVO getQnaBoard(Map<String, Integer> map);
	public List<QnaBoardVO> searchQnaBoard(Map<String, Object> map);
	public QnaSetVO getQnaComments(int qna_no);
	public List<FAQBoardVO> getFAQBoardList();
	
	
	public List<ReportBoardVO> searchReportBoard(String searchText);
	public ReportSetVO getReportBoardOne(int rptNo);
	public ReportSetVO getReportComments(int rpt_mem_no);
	
	public List<ReportSetVO> getReportBoardList();
	
	
}
