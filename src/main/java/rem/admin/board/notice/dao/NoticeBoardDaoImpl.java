package rem.admin.board.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.board.notice.vo.NoticeBoardVO;
import utill.MyBatisUtil;

public class NoticeBoardDaoImpl implements INoticeBoardDao {
	private static INoticeBoardDao instance;
	
	private NoticeBoardDaoImpl() {}
	
	public static INoticeBoardDao getInstance() {
		if(instance == null) instance = new NoticeBoardDaoImpl();
		return instance;
	}
	
	@Override
	public List<NoticeBoardVO> selectAllNoticeBoard() {
		SqlSession session = null;
		List<NoticeBoardVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("noticeBoard.selectAllNoticeBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

	@Override
	public NoticeBoardVO selectNoticeBoard(int no) {
		SqlSession session = null;
		NoticeBoardVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("noticeBoard.selectNoticeBoard", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return vo;
	}

	@Override
	public int insertNoticeBoard(NoticeBoardVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("noticeBoard.insertNoticeBoard", vo);
			System.out.println("다오 작동확인 : " + vo);
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return res;
	}

	@Override
	public int updateNoticeBoard(NoticeBoardVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("noticeBoard.updateNoticeBoard", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return res;
	}

	@Override
	public int deleteNoticeBoard(int no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.delete("noticeBoard.deleteNoticeBoard", no);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return res;
	}

	@Override
	public int updateNoticeCnt(int no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("noticeBoard.updateNoticeCnt", no);

			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public List<NoticeBoardVO> searchNoticeBoard(Map<String, String> map) {
		SqlSession session = null;
		List<NoticeBoardVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("noticeBoard.searchNoticeBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return list;
	}

}
