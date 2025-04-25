package rem.admin.board.qna.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.qna.dao.IQnaDao;
import rem.admin.board.qna.dao.QnaDaoImpl;
import rem.admin.board.qna.vo.QnaBoardVO;
import utill.MyBatisUtil;

public class QnaServiceImpl implements IQnaService {
	// dao 가져오기
	private IQnaDao dao;
	
	// 서비스
	private static QnaServiceImpl service;
	
	// 생성자 막기
	private QnaServiceImpl() {
		dao = QnaDaoImpl.getinstance();
	}
	
	// 최초 한번 생성자에서 서비스 생성하기 
	public static QnaServiceImpl getInstance() {
		if(service == null) service = new QnaServiceImpl();
		return service;
	}

	/**
	 *	게시글 목록(리스트) 
	 *	return: List<QnaVO> 
	 */
	@Override
	public List<QnaBoardVO> selectQnaList() {
		return dao.selectQnaList();
	}
	
	/**
	 *	게시글 보기 
	 *	parameter: 게시글 번호
	 *	return: QnaVO 
	 */
	@Override
	public QnaBoardVO seletQnaView(int qna_no) {
		return dao.seletQnaView(qna_no);
	}

	/**
	 *	게시글 삭제 
	 *	parameter: 게시글 번호
	 *	return: 0ㅣ 삭제 실패, 1: 삭제 성공 
	 */
	@Override
	public int deleteQna(int qna_no) {
		return dao.deleteQna(qna_no);
	}

	/**
	 *	게시글 검색 
	 *	parameter: 게시글 번호
	 *	return: 0ㅣ 삭제 실패, 1: 삭제 성공 
	 */
	@Override
	public List<QnaBoardVO> searchQna(Map<String, String> map) {
		return dao.searchQna(map);
	}

	/**
	 *  회원 문의게시글 작성
	 *  @param QanBoardVO - qna_status && qna_com_status default 0, qna_at default sysdate
	 *  @return 0ㅣ 실패, 1ㅣ 성공
	 *  @author KCY
	 */
	@Override
	public int insertQna(QnaBoardVO vo) {
		return dao.insertQna(vo);
	}

	@Override
	public int getQnaNoImmediately(int mem_no) {
		return dao.getQnaNoImmediately(mem_no);
	}

}
