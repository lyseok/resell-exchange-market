package rem.admin.board.report.service;

import rem.admin.board.report.vo.ReportCommentsVO;

public interface IReportCommentsService {
	public ReportCommentsVO selectReportComments(int no);
	public int insertReportComments(ReportCommentsVO vo);
	public int updateReportComments(ReportCommentsVO vo);
	public int deleteReportComments(int no);
}
