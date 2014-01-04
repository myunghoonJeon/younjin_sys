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

	public List<Code> getCodeList(String mainCode) {
		return getSqlSession().selectList("codeMapper.getCodeList", mainCode);
	}

	public Code getCode(Code code) {
		return getSqlSession().selectOne("codeMapper.getCode", code);
	}

	public List<Code> getCarrierList() {
		return getSqlSession().selectList("codeMapper.getCodeList", "04");
	}
}
