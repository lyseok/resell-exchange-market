package rem.admin.board.notice.service;

import java.util.List;
import java.util.Map;

import rem.admin.board.notice.dao.INoticeBoardDao;
import rem.admin.board.notice.vo.NoticeBoardVO;

public class NoticeBoardServiceImpl implements INoticeBoardService {
	private static INoticeBoardService instance;
	private INoticeBoardDao dao;
	
	private NoticeBoardServiceImpl(INoticeBoardDao dao) {
		this.dao = dao;
	}
	
	public static INoticeBoardService getInstance(INoticeBoardDao dao) {
		if(instance == null) instance = new NoticeBoardServiceImpl(dao);
		return instance;
	}
	@Override
	public List<NoticeBoardVO> selectAllNoticeBoard() {
		return dao.selectAllNoticeBoard();
	}

	@Override
	public NoticeBoardVO selectNoticeBoard(int no) {
		int res = updateNoticeCnt(no);
		if(res == 0) return null;
		return dao.selectNoticeBoard(no);
	}

	@Override
	public int insertNoticeBoard(NoticeBoardVO vo) {
		System.out.println("서비스 작동확인 : " + vo);
		return dao.insertNoticeBoard(vo);
	}

	@Override
	public int updateNoticeBoard(NoticeBoardVO vo) {
		return dao.updateNoticeBoard(vo);
	}

	@Override
	public int deleteNoticeBoard(int no) {
		return dao.deleteNoticeBoard(no);
	}

	@Override
	public int updateNoticeCnt(int no) {
		return dao.updateNoticeCnt(no);
	}

	@Override
	public List<NoticeBoardVO> searchNoticeBoard(Map<String, String> map) {
		return dao.searchNoticeBoard(map);
	}

}
