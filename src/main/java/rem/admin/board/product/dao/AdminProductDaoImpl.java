package rem.admin.board.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.product.vo.ProductVO;
import utill.MyBatisUtil;

public class AdminProductDaoImpl implements IAdminProductDao {
	private static AdminProductDaoImpl dao;
	
	private AdminProductDaoImpl() {}
	
	public static AdminProductDaoImpl getInstence() {
		if(dao==null) dao = new AdminProductDaoImpl();
		return dao;
	}

	@Override
	public List<ProductVO> selectAdminProductList() {
		SqlSession session = null;
		List<ProductVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.selectAdminProductList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return list;
	}

	@Override
	public List<ProductVO> searchAdminProduct(Map<String, String> map) {
		SqlSession session = null;
		List<ProductVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.searchAdminProduct", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return list;
	}

}
