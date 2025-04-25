package rem.admin.main.service;

import java.util.List;

import rem.admin.main.dao.IAdminMainDao;
import rem.admin.main.vo.AdminTransVO;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;

public class AdminMainServiceImpl implements IAdminMainService {
	private static IAdminMainService instance;
	private IAdminMainDao dao;
	
	private AdminMainServiceImpl(IAdminMainDao dao) {
		this.dao = dao;
	}
	
	public static IAdminMainService getInstance(IAdminMainDao dao) {
		if(instance == null) instance = new AdminMainServiceImpl(dao);
		return instance;
	}
	
	
	@Override
	public List<MemberVO> selectAdminMainMember() {
		return dao.selectAdminMainMember();
	}

	@Override
	public List<ProductVO> selectAdminMainProduct() {
		return dao.selectAdminMainProduct();
	}

	@Override
	public List<AdminTransVO> selectAdminMainTransact() {
		return dao.selectAdminMainTransact();
	}

}
