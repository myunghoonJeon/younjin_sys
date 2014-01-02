package org.youngjin.net.memorandum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Memorandum> getMemorandumList(String seq, Integer memorandumSeq) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("seq", Integer.parseInt(seq));
		param.put("memorandumSeq", memorandumSeq);
		
		return getSqlSession().selectList("memorandumMapper.getMemorandumList", param);
	}

	public void deleteMemorandum(Memorandum paramMemorandum) {
		getSqlSession().delete("memorandumMapper.deleteMemorandum", paramMemorandum);
	}

	public void updateMemorandum(Memorandum memorandum) {
		getSqlSession().update("memorandumMapper.updateMemorandum", memorandum);
	}

	public List<MemorandumList> getMemorandumAllList(String seq) {
		return getSqlSession().selectList("memorandumMapper.getMemroandumAllList", seq);
	}

	public void addMemorandumList(MemorandumList memorandumList) {
		getSqlSession().insert("memorandumMapper.addMemorandumList", memorandumList);	
	}

	public MemorandumList getMemorandumListSelectOne(
			MemorandumList memorandumList) {
		return getSqlSession().selectOne("memorandumMapper.getMemorandumListSelectOne", memorandumList);
	}

	public void insertInvoiceMemorandum(Memorandum memorandum) {
		getSqlSession().insert("memorandumMapper.insertInvoiceMemorandum", memorandum);
	}
	
}
