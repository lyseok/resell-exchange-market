package rem.login.service;

import java.util.Map;

import rem.login.vo.MemberVO;
import rem.login.vo.UpdateMemberVO;

public interface IMemberService {
	public MemberVO login(Map<String, String> map);
	public int idCheck(String email);
	public int insertMember(MemberVO vo);
	public int pwCheck(Map<String, String> map);
	public UpdateMemberVO selectUpdateMember(int no);
	public int updateMember(UpdateMemberVO vo);
	public int updateAddress(UpdateMemberVO vo);
	public MemberVO getMemInfo(int mem_no);
}
