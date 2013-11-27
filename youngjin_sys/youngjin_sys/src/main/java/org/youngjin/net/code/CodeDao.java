package org.youngjin.net.code;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDao extends SqlSessionDaoSupport {

	public List<Code> getAllAuthList() {
		return getSqlSession().selectList("codeMapper.getCodeList", "02");
	}

	public List<Code> getAllAreaList() {
		return getSqlSession().selectList("codeMapper.getCodeList", "01");
	}
}
