package org.youngjin.net.basic;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BasicDao extends SqlSessionDaoSupport {

	public List<Branch> getBranch(Branch branch) {
		return getSqlSession().selectList("basicMapper.getBranch", branch);
	}

	public void insertBranch(Branch branch) {
		getSqlSession().insert("basicMapper.insertBranch", branch);
	}

}
