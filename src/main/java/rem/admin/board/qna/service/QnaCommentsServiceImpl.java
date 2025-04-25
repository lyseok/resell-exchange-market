package rem.admin.board.qna.service;

import rem.admin.board.qna.dao.IQnaCommentsDao;
import rem.admin.board.qna.dao.QnaCommentsDaoImpl;
import rem.admin.board.qna.vo.QnaCommentsVO;

public class QnaCommentsServiceImpl implements IQnaCommentsService {
	private static QnaCommentsServiceImpl service;
	
	private IQnaCommentsDao dao;
	
	private QnaCommentsServiceImpl() {
		dao = QnaCommentsDaoImpl.getInstence();
	}
	public static QnaCommentsServiceImpl getInstence() {
		if(service==null) service = new QnaCommentsServiceImpl();
		return service;
	}
	

	@Override
	public QnaCommentsVO selectQnaCmt(int qna_no) {
		return dao.selectQnaCmt(qna_no);
	}
	@Override
	public int insertQnaComments(QnaCommentsVO qnaVO) {
		return dao.insertQnaComments(qnaVO);
	}
	@Override
	public int updateQnaComments(QnaCommentsVO qnaVO) {
		return dao.updateQnaComments(qnaVO);
	}

}
