package rem.login.service;

import java.util.Map;

import rem.login.dao.IMemberDao;
import rem.login.vo.MemberVO;
import rem.login.vo.UpdateMemberVO;

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
	public MemberVO login(Map<String, String> map) {
		return dao.login(map);
	}

	@Override
	public int idCheck(String email) {
		return dao.idCheck(email);
	}

	@Override
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public int pwCheck(Map<String, String> map) {
		return dao.pwCheck(map);
	}

	@Override
	public UpdateMemberVO selectUpdateMember(int no) {
		return dao.selectUpdateMember(no);
	}

	@Override
	public int updateMember(UpdateMemberVO vo) {
		if(dao.updateMember(vo) == 0) return 0;
		return updateAddress(vo);
	}

	@Override
	public int updateAddress(UpdateMemberVO vo) {
		return dao.updateAddress(vo);
	}

	@Override
	public MemberVO getMemInfo(int mem_no) {
		// TODO Auto-generated method stub
		return dao.getMemInfo(mem_no);
	}

	

}
