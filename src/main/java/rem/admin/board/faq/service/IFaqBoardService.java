package rem.admin.board.faq.service;

import java.util.List;


import rem.admin.board.faq.vo.FAQBoardVO;
import rem.admin.board.notice.vo.NoticeBoardVO;

public interface IFaqBoardService {


	public List<FAQBoardVO> selectFaqBoardList();
	
	public List<FAQBoardVO> selectByType(String faq_type);
	
	public FAQBoardVO selectFaqView(int faq_no);
	
	public int insertFaq(FAQBoardVO FAQBoardVO);
	
	public int updateFaq(FAQBoardVO FAQBoardVO);
	
	public int deleteFaq(int faq_no);
	
	
	
	
}
