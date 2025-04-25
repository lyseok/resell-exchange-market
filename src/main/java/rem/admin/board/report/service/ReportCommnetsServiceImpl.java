package rem.admin.board.report.service;

import rem.admin.board.report.dao.IReportCommentsDao;
import rem.admin.board.report.vo.ReportCommentsVO;

public class ReportCommnetsServiceImpl implements IReportCommentsService {
	private static IReportCommentsService instance;
	private IReportCommentsDao dao;
	
	private ReportCommnetsServiceImpl(IReportCommentsDao dao) {
		this.dao = dao;
	}
	
	public static IReportCommentsService getInstance(IReportCommentsDao dao) {
		if(instance == null) instance = new ReportCommnetsServiceImpl(dao);
		return instance;
	}

	@Override
	public ReportCommentsVO selectReportComments(int no) {
		return dao.selectReportComments(no);
	}

	@Override
	public int insertReportComments(ReportCommentsVO vo) {
		return dao.insertReportComments(vo);
	}

	@Override
	public int updateReportComments(ReportCommentsVO vo) {
		return dao.updateReportComments(vo);
	}

	@Override
	public int deleteReportComments(int no) {
		return dao.deleteReportComments(no);
	}

}
