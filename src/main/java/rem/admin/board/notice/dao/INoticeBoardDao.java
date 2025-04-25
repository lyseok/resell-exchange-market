package rem.admin.board.notice.dao;

import java.util.List;
import java.util.Map;

import rem.admin.board.notice.vo.NoticeBoardVO;

public interface INoticeBoardDao {
	public List<NoticeBoardVO> selectAllNoticeBoard();
	public List<NoticeBoardVO> searchNoticeBoard(Map<String, String> map);
	public NoticeBoardVO selectNoticeBoard(int no);
	public int insertNoticeBoard(NoticeBoardVO vo);
	public int updateNoticeBoard(NoticeBoardVO vo);
	public int deleteNoticeBoard(int no);
	public int updateNoticeCnt(int no);
}
