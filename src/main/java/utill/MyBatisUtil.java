package utill;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory = null;
	
	static {
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(
					"myBatis/config/mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패");
			e.printStackTrace();
		} finally {
			if(in != null) try { in.close(); } catch(IOException e) { } 
		}
	}
	
	// SqlSession객체를 반환하는 메서드
	public static SqlSession getSqlSession() {
		SqlSession session = factory.openSession();
		return session;
	}
	
	// autoCommit이 true이면 AutoCoomit이 활성화된 상태
	// 			   false이면 AutoCommit이 비활성화된 상태
	public static SqlSession getSqlSession(boolean autoCommit) {
		SqlSession session = factory.openSession(autoCommit);
		return session;
	}
}
