package rem.wishlist.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.product.vo.ProductVO;
import rem.wishlist.vo.WishlistVO;
import utill.MyBatisUtil;

public class WishlistDaoImpl implements IWishlistDao {
	private static IWishlistDao instance;
	
	private WishlistDaoImpl() {}
	
	public static IWishlistDao getInstance() {
		if(instance == null) instance = new WishlistDaoImpl();
		return instance;
	}
	@Override
	public int countWishlist(int mem_no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.selectOne("wishlist.countWishlist",mem_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}

	@Override
	public List<ProductVO> getStoreWishList(int storeId) {
		SqlSession session = null;
		List<ProductVO> list = new ArrayList<>();
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("store.getStoreWishlist", storeId);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return list;
	}

	@Override
	public int insertWish(WishlistVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.insert("wishlist.insertWish", vo);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int distinctWish(WishlistVO vo) {
		SqlSession session = null;
		int mem_no = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			mem_no = session.selectOne("wishlist.distinctWish", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return mem_no;
	}

	@Override
	public int deleteWish(WishlistVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.delete("wishlist.deleteWish", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int countProdWish(int prod_no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			cnt = session.selectOne("wishlist.countProdWish", prod_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

}
