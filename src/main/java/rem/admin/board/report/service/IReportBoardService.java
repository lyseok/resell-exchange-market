package rem.admin.board.report.service;

import java.util.List;

import rem.admin.board.report.vo.ReportBoardVO;

public interface IReportBoardService {
	public List<ReportBoardVO> selectAllReportBoard();
	public ReportBoardVO selectReportBoard(int no);
	public int insertReportBoard(ReportBoardVO vo);
	public int deleteReportBoard(int no);
	
	public int getReportNoImmediately(int mem_no);
}
