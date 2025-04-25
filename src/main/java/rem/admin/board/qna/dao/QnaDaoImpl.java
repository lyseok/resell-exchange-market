package rem.admin.board.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.qna.vo.QnaBoardVO;
import utill.MyBatisUtil;

public class QnaDaoImpl implements IQnaDao {
	
	private static QnaDaoImpl dao = null;
	
	// 생성자 막기
	private QnaDaoImpl() {}
	
	public static QnaDaoImpl getinstance() {
		if(dao == null) dao = new QnaDaoImpl();
		return dao;
	}

	@Override
	public List<QnaBoardVO> selectQnaList() {
		SqlSession session = null;
		List<QnaBoardVO> qnaList = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			qnaList = session.selectList("qna.selectQnaList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return qnaList;
	}

	
	@Override
	public QnaBoardVO seletQnaView(int qna_no) {
		SqlSession session = null;
		QnaBoardVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("qna.seletQnaView", qna_no);
			System.out.println(vo.getMem_no());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return vo;
	}

	
	
	@Override
	public int deleteQna(int qna_no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.delete("qna.deleteQna", qna_no);
			if(cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<QnaBoardVO> searchQna(Map<String, String> map) {
		SqlSession session = null;
		List<QnaBoardVO> qnaList = null;
		try {
			session = MyBatisUtil.getSqlSession();
			qnaList = session.selectList("qna.searchQna", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return qnaList;
	}

	@Override
	public int insertQna(QnaBoardVO vo) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("qna.insertQna", vo);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}

	@Override
	public int getQnaNoImmediately(int mem_no) {
		SqlSession session = null;
		int qna_no = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			qna_no = session.selectOne("qna.getQnaNoImmediately", mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return qna_no;
	}

}
