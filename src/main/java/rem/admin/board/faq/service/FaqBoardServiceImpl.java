package rem.admin.board.faq.service;

import java.util.List;

import rem.admin.board.faq.dao.FaqBoardDaoImpl;
import rem.admin.board.faq.dao.IFaqBoardDao;
import rem.admin.board.faq.vo.FAQBoardVO;

public class FaqBoardServiceImpl implements IFaqBoardService {

	private static IFaqBoardService service;
	
	private IFaqBoardDao dao;
	
	private FaqBoardServiceImpl() {
		dao = FaqBoardDaoImpl.getinstance();
	}
	public static IFaqBoardService getinstance() {
		if(service == null) service = new FaqBoardServiceImpl();
		return service;
	}
		
	
	@Override
	public List<FAQBoardVO> selectFaqBoardList() {
		// TODO Auto-generated method stub
		return dao.selectFaqBoardList();
	}

	@Override
	public FAQBoardVO selectFaqView(int faq_no) {
		// TODO Auto-generated method stub
		return dao.selectFaqView(faq_no);
	}

	@Override
	public int insertFaq(FAQBoardVO FAQBoardVO) {
		// TODO Auto-generated method stub
		return dao.insertFaq(FAQBoardVO);
	}

	@Override
	public int updateFaq(FAQBoardVO FAQBoardVO) {
		// TODO Auto-generated method stub
		return dao.updateFaq(FAQBoardVO);
	}

	@Override
	public int deleteFaq(int faq_no) {
		// TODO Auto-generated method stub
		return dao.deleteFaq(faq_no);
	}
	@Override
	public List<FAQBoardVO> selectByType(String faq_type) {
		// TODO Auto-generated method stub
		return dao.selectByType(faq_type);
	}

	
}
