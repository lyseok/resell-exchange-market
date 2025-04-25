package rem.admin.board.report.dao;

import rem.admin.board.report.vo.ReportCommentsVO;

public interface IReportCommentsDao {
	public ReportCommentsVO selectReportComments(int no);
	public int insertReportComments(ReportCommentsVO vo);
	public int updateReportComments(ReportCommentsVO vo);
	public int deleteReportComments(int no);
}
