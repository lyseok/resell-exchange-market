package rem.admin.product.wishlist.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.admin.product.wishlist.vo.WishlistAllVO;
import utill.MyBatisUtil;

public class WishlistAllDaoImpl implements IWishlistAllDao {

	private static IWishlistAllDao dao;
	
	private WishlistAllDaoImpl() {}
	
	public static IWishlistAllDao getinstace() {
		if(dao == null) dao = new WishlistAllDaoImpl();
		return dao;
	}
	@Override
	public List<WishlistAllVO> selectboardlist() {
		SqlSession session = null;
		List<WishlistAllVO> wavo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			wavo = session.selectList("wishlistall.getwishlistall");
			System.out.println("다오에서 보내줄 LIST" + wavo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}return wavo;
			}

	@Override
	public List<WishlistAllVO> searchboardlist(Map<String, String> map) {
		SqlSession session = null;
		List<WishlistAllVO> wavo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			wavo = session.selectList("wishlistall.searchwishlistall" ,map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return wavo;
	}


	
	

}
