package rem.admin.board.report.dao;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.report.vo.ReportCommentsVO;
import utill.MyBatisUtil;

public class ReportCommentsDaoImpl implements IReportCommentsDao {
	private static IReportCommentsDao instance;
	
	private ReportCommentsDaoImpl() {}
	
	public static IReportCommentsDao getInstance() {
		if(instance == null) instance = new ReportCommentsDaoImpl();
		return instance;
	}
	
	@Override
	public ReportCommentsVO selectReportComments(int no) {
		SqlSession session = null;
		ReportCommentsVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("reportComments.selectReportComments", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return vo;
	}

	@Override
	public int insertReportComments(ReportCommentsVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("reportComments.insertReportComments", vo);
			
			if(res > 0) session.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int updateReportComments(ReportCommentsVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("reportComments.updateReportComments", vo);
			
			if(res > 0) session.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int deleteReportComments(int no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.delete("reportComments.deleteReportComments", no);
			
			if(res > 0) session.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

}
