package rem.admin.member.service;

import java.util.List;

import rem.login.vo.MemberVO;

public interface IMemberService {
	public List<MemberVO> selectAllMemberList();
	public int banMember(int no);
}
