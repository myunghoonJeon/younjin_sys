package org.youngjin.net.memorandum;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemorandumDao extends SqlSessionDaoSupport {

	public Memorandum getMemorandum(Memorandum paramMemorandum) {
		return getSqlSession().selectOne("memorandumMapper.getMemorandum", paramMemorandum);
	}

	public void insertMemorandum(Memorandum memorandum) {
		getSqlSession().insert("memorandumMapper.insertMemorandum", memorandum);
	}

	public List<Memorandum> getMemorandumList(String seq) {
		return getSqlSession().selectList("memorandumMapper.getMemorandumList", seq);
	}

	public void deleteMemorandum(Memorandum paramMemorandum) {
		getSqlSession().delete("memorandumMapper.deleteMemorandum", paramMemorandum);
	}
	
}
