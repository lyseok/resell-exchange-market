package rem.admin.board.qna.dao;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.qna.vo.QnaCommentsVO;
import utill.MyBatisUtil;

public class QnaCommentsDaoImpl implements IQnaCommentsDao{
	private static QnaCommentsDaoImpl dao;
	
	// 생성자 막기
	private QnaCommentsDaoImpl() {}
	
	public static QnaCommentsDaoImpl getInstence() {
		if(dao==null) dao = new QnaCommentsDaoImpl();
		return dao;
	}
	
	@Override
	public QnaCommentsVO selectQnaCmt(int qna_no) {
		SqlSession session = null;
		QnaCommentsVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("qnacmt.selectQnaCmt", qna_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return vo;
	}

	@Override
	public int insertQnaComments(QnaCommentsVO qnaVO) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("qnacmt.insertQnaComments", qnaVO);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateQnaComments(QnaCommentsVO qnaVO) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.update("qnacmt.updateQnaComments", qnaVO);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

}
