package rem.admin.board.report.service;

import java.util.List;

import rem.admin.board.report.dao.IReportBoardDao;
import rem.admin.board.report.vo.ReportBoardVO;

public class ReportBoardServiceImpl implements IReportBoardService {
	private static IReportBoardService instance;
	private IReportBoardDao dao;
	
	private ReportBoardServiceImpl(IReportBoardDao dao) {
		this.dao = dao;
	}
	
	public static IReportBoardService getInstance(IReportBoardDao dao) {
		if(instance == null) instance = new ReportBoardServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<ReportBoardVO> selectAllReportBoard() {
		return dao.selectAllReportBoard();
	}

	@Override
	public ReportBoardVO selectReportBoard(int no) {
		return dao.selectReportBoard(no);
	}

	@Override
	public int insertReportBoard(ReportBoardVO vo) {
		return dao.insertReportBoard(vo);
	}

	@Override
	public int deleteReportBoard(int no) {
		return dao.deleteReportBoard(no);
	}

	@Override
	public int getReportNoImmediately(int mem_no) {
		return dao.getReportNoImmediately(mem_no);
	}



}
