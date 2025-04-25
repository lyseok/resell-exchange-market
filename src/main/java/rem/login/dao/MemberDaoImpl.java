package rem.login.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.login.vo.MemberVO;
import rem.login.vo.UpdateMemberVO;
import utill.MyBatisUtil;

public class MemberDaoImpl implements IMemberDao {
	private static IMemberDao instance = null;
	
	private MemberDaoImpl() {}
	
	public static IMemberDao getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	}
	
	@Override
	public MemberVO login(Map<String, String> map) {
		SqlSession session = null;
		MemberVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("member.login", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return vo;
	}

	@Override
	public int idCheck(String email) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.selectOne("member.idCheck", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int insertMember(MemberVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.insert("member.insertMember", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int pwCheck(Map<String, String> map) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.selectOne("member.pwCheck", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public UpdateMemberVO selectUpdateMember(int no) {
		SqlSession session = null;
		UpdateMemberVO vo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			vo = session.selectOne("member.selectUpdateMember", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			if(session != null) session.close();
		}
		return vo;
	}

	@Override
	public int updateMember(UpdateMemberVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("member.updateMember", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public int updateAddress(UpdateMemberVO vo) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			res = session.update("member.updateAddress", vo);
			
			if(res > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return res;
	}

	@Override
	public MemberVO getMemInfo(int mem_no) {
		SqlSession session = null;
		MemberVO mvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			mvo = session.selectOne("member.getMemInfo", mem_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return mvo;
	}

	

}
