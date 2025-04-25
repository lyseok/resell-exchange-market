package rem.store.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.login.vo.MemberVO;
import rem.product.vo.ProdImgVO;
import rem.review.vo.ReviewImgVO;
import utill.MyBatisUtil;

public class StoreDaoImpl implements IStoreDao{
	private static StoreDaoImpl dao;
	private StoreDaoImpl() { }
	public static StoreDaoImpl getInstance() {
		if(dao==null) dao = new StoreDaoImpl();
		return dao;
	}
	@Override
	public int getProgSell(int storeId) {
		SqlSession session = null;
		int countProgSell = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			countProgSell = session.selectOne("store.getProgSell", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return countProgSell;
	}
	@Override
	public int getCountAllProducts(int storeId) {
		SqlSession session = null;
		int countAllProducts = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			countAllProducts = session.selectOne("store.getCountAllProducts", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return countAllProducts;
	}
	@Override
	public int getCountSoldoutProducts(int storeId) {
		SqlSession session = null;
		int countSoldoutProducts = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			countSoldoutProducts = session.selectOne("store.getCountSoldoutProducts", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return countSoldoutProducts;
	}
	@Override
	public int updateMypageProfile(Map<String, Object> editedMap) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.update("store.updateMypageProfile", editedMap);
			if(rec>0) session.commit();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}
	@Override
	public MemberVO getStoreInfoByProd(int prod_no) {
		SqlSession session = null;
		MemberVO memberVO = new MemberVO();
		try {
			session = MyBatisUtil.getSqlSession();
			memberVO = session.selectOne("store.getStoreInfoByProd", prod_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return memberVO;
	}
	@Override
	public MemberVO getStoreInfoByMem(int mem_no) {
		SqlSession session = null;
		MemberVO memberVO = new MemberVO();
		try {
			session = MyBatisUtil.getSqlSession();
			memberVO = session.selectOne("store.getStoreInfoByMem", mem_no);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return memberVO;
	}
	@Override
	public List<ProdImgVO> getStoreProdList(int storeId) {
		SqlSession session = null;
		List<ProdImgVO> list = new ArrayList<>();
		try{
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreProdList", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ProdImgVO> getStoreSoldoutList(int storeId) {
		SqlSession session = null;
		List<ProdImgVO> list = new ArrayList<>();
		try{
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreSoldoutList", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ProdImgVO> getStoreWishlist(int storeId) {
		SqlSession session = null;
		List<ProdImgVO> list = new ArrayList<>();
		try{
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreWishlist", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public List<ReviewImgVO> getStoreReviewList(int storeId) {
		SqlSession session = null;
		List<ReviewImgVO> list = new ArrayList<>();
		try{
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreReviewList", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}
	@Override
	public double storeRatingAvg(int storeId) {
		SqlSession session = null;
		double rating = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rating = session.selectOne("store.storeRatingAvg", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rating;
	}
	
	
}
