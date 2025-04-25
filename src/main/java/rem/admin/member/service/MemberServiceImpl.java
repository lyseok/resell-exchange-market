package rem.admin.member.service;

import java.util.List;

import rem.admin.member.dao.IMemberDao;
import rem.login.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private static IMemberService instance;
	private IMemberDao dao;
	
	private MemberServiceImpl(IMemberDao dao) {
		this.dao = dao;
	}
	
	public static IMemberService getInstance(IMemberDao dao) {
		if(instance == null) instance = new MemberServiceImpl(dao);
		return instance;
	}
	
	@Override
	public List<MemberVO> selectAllMemberList() {
		return dao.selectAllMemberList();
	}

	@Override
	public int banMember(int no) {
		return dao.banMember(no);
	}

}
