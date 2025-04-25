package rem.admin.main.service;

import java.util.List;

import rem.admin.main.vo.AdminTransVO;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;

public interface IAdminMainService {
	public List<MemberVO> selectAdminMainMember();
	public List<ProductVO> selectAdminMainProduct();
	public List<AdminTransVO> selectAdminMainTransact();
	
}
