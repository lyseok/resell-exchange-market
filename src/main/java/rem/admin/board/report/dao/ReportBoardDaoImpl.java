package rem.admin.board.report.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.report.vo.ReportBoardVO;
import utill.MyBatisUtil;

public class ReportBoardDaoImpl implements IReportBoardDao {
	private static IReportBoardDao instance;
	
	private ReportBoardDaoImpl() {}
	
	public static IReportBoardDao getInstance() {
		if(instance == null) instance = new ReportBoardDaoImpl();
		return instance;
	}

	@Override
	public List<ReportBoardVO> selectAllReportBoard() {
		SqlSession session = null;
		List<ReportBoardVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("reportBoard.selectAllReportBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public ReportBoardVO selectReportBoard(int no) {
		SqlSession session = null;
		ReportBoardVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("reportBoard.selectReportBoard", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return vo;
	}

	@Override
	public int insertReportBoard(ReportBoardVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("reportBoard.insertReportBoard", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int deleteReportBoard(int no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res= session.delete("reportBoard.deleteReportBoard", no);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
				
		return res;
	}

	@Override
	public int getReportNoImmediately(int mem_no) {
		SqlSession session = null;
		int rpt_no = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rpt_no = session.selectOne("reportBoard.getReportNoImmediately", mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rpt_no;
	}
	

}









