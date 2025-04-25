package rem.admin.product.view.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.product.vo.ViewCountVO;
import utill.MyBatisUtil;

public class ViewDaoImpl implements IViewDao {
	
	private static IViewDao dao;
	
	private ViewDaoImpl() {}
	
	public static IViewDao getinstace() {
		if(dao == null) dao = new ViewDaoImpl();
		return dao;
	}

	@Override
	public List<ViewCountVO> selectboardlist() {
		SqlSession session = null;
		List<ViewCountVO> vcvo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vcvo = session.selectList("view.getviewlist");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return vcvo;
	}

	@Override
	public List<ViewCountVO> searchboardlist(Map<String, String> map) {
		SqlSession session = null;
		List<ViewCountVO> vcvo = null;
		try {
			session = MyBatisUtil.getSqlSession();
			vcvo = session.selectList("view.searchviewlist", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		return vcvo;
	}

}
