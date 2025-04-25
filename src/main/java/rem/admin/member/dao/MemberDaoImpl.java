package rem.admin.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import rem.login.vo.MemberVO;
import utill.MyBatisUtil;

public class MemberDaoImpl implements IMemberDao {
	private static IMemberDao instance;
	
	private MemberDaoImpl() {};
	
	public static IMemberDao getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	}
	
	@Override
	public List<MemberVO> selectAllMemberList() {
		SqlSession session = null;
		List<MemberVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("member.selectAllMemberList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return list;
	}

	@Override
	public int banMember(int no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("member.updateBanMember", no);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

}
