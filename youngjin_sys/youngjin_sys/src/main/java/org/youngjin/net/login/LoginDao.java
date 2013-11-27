package org.youngjin.net.login;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.code.Code;
import org.youngjin.net.util.UserFilter;

@Repository
public class LoginDao extends SqlSessionDaoSupport {

	public void insertUser(User user) {
		getSqlSession().insert("loginMapper.insertUser", user);		
	}

	public void deleteUserByUsername(String username) {
		getSqlSession().delete("loginMapper.deleteUserByUsername", username);
		
	}

	public User selectUser(Integer seq) {
		return getSqlSession().selectOne("loginMapper.selectUser", seq);
	}

	public void updatePassword(User user) {
		getSqlSession().update("loginMapper.updatePassword", user);
	}

	public void insertGroups(User user) {
		getSqlSession().insert("loginMapper.insertGroups", user);		
	}

	public void insertGroupAuthorities(User user) {		
		getSqlSession().insert("loginMapper.insertGroupAuthorities", user);		
	}

	public void updateUserByAdmin(User user) {
		getSqlSession().update("loginMapper.updateUserByAdmin", user);		
	}

	public int selectUserCount(UserFilter userFilter) {
		return getSqlSession().selectOne("loginMapper.selectUserCount", userFilter);
	}

	public void deleteUserBySeq(Integer seq) {
		getSqlSession().delete("loginMapper.deleteUserBySeq", seq);
		
	}

	public String getUserIndex() {
		return getSqlSession().selectOne("loginMapper.getUserIndex");
	}
}
