package rem.file.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.file.vo.ImgFileVO;
import utill.MyBatisUtil;

public class FileDaoImpl implements IFileDao {
	private static FileDaoImpl dao;
	private FileDaoImpl() { }
	public static FileDaoImpl getInstance() {
		if(dao==null) dao = new FileDaoImpl();
		return dao;
	}
	@Override
	public int insertProfileImg(ImgFileVO imgVO) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("imgfile.insertProfileImg", imgVO);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public ImgFileVO getProfileImg(int mem_no) {
		SqlSession session = null;
		ImgFileVO imgVO = new ImgFileVO();
		try {
			session = MyBatisUtil.getSqlSession();
			imgVO = session.selectOne("imgfile.getProfileImg", mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return imgVO;
	}
	@Override
	public ImgFileVO getProfileNull() {
		SqlSession session = null;
		ImgFileVO imgVO = new ImgFileVO();
		try {
			session = MyBatisUtil.getSqlSession();
			imgVO = session.selectOne("imgfile.getProfileNull");
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return imgVO;
	}
	@Override
	public int countProfileImg(int mem_no) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.selectOne("imgfile.countProfileImg", mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public int deleteProfileImg(int mem_no) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.delete("imgfile.deleteProfileImg", mem_no);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public ImgFileVO getStoreTabProdImg(int prod_no) {
		SqlSession session = null;
		ImgFileVO vo = new ImgFileVO();
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("store.getStoreTabProdImg", prod_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}
	@Override
	public int insertProductImg(ImgFileVO imgVO) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("imgfile.insertProductImg", imgVO);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public List<ImgFileVO> getStoreProdImg(int storeId) {
		SqlSession session = null;
		List<ImgFileVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreProdImg", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ImgFileVO> getStoreReviewImg(int storeId) {
		SqlSession session = null;
		List<ImgFileVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreReviewImg", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}	
	@Override
	public List<ImgFileVO> getStoreSoldoutImg(int storeId) {
		SqlSession session = null;
		List<ImgFileVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreSoldoutImg", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ImgFileVO> getStoreWishImg(int storeId) {
		SqlSession session = null;
		List<ImgFileVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreWishImg", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ImgFileVO> getProductfileImg(int prod_no) {
		SqlSession session = null;
		List<ImgFileVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			list = session.selectList("imgfile.getProductfileImg", prod_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return list;
	}
	@Override
	public int deleteProductImg(int prod_no) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.delete("imgfile.deleteProductImg", prod_no);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public int insertQnaImg(ImgFileVO imgVO) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("imgfile.insertQnaImg", imgVO);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public ImgFileVO getQnaImg(int qna_no) {
		SqlSession session = null;
		ImgFileVO vo = null;
		try{
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("imgfile.getQnaImg", qna_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}
	@Override
	public int insertQnaCommentImg(ImgFileVO imgVO) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("imgfile.insertQnaCommentImg", imgVO);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public ImgFileVO getQnaCommentImg(int qna_no) {
		SqlSession session = null;
		ImgFileVO vo = null;
		try{
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("imgfile.getQnaCommentImg", qna_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return vo;
	}
	
	@Override
	public int insertReportImg(ImgFileVO imgVO) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.insert("imgfile.insertReportImg", imgVO);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}

}
