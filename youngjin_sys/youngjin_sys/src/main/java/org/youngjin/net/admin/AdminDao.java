package org.youngjin.net.admin;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.Container;
import org.youngjin.net.login.User;

@Repository
public class AdminDao extends SqlSessionDaoSupport {

	public List<User> getAllUserList() {
		return getSqlSession().selectList("adminMapper.getUserList");
	}

	public List<Container> getContainerList() {
		return getSqlSession().selectList("adminMapper.getContainerList");
	}

}
