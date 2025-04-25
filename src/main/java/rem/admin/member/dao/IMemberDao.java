package rem.admin.member.dao;

import java.util.List;

import rem.login.vo.MemberVO;

public interface IMemberDao {
	public List<MemberVO> selectAllMemberList();
	public int banMember(int no);
}
