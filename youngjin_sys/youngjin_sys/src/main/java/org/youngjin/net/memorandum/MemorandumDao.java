package org.youngjin.net.memorandum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemorandumDao extends SqlSessionDaoSupport {

	public Memorandum getMemorandum(Memorandum paramMemorandum) {
		Memorandum memorandum = getSqlSession().selectOne("memorandumMapper.getMemorandum", paramMemorandum);
		
		if(memorandum != null)
			return getSqlSession().selectOne("memorandumMapper.getMemorandum", paramMemorandum);
		else 
			return new Memorandum();
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
	
	public List<Memorandum> getMemorandumListIb(String seq, Integer memorandumSeq) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("seq", Integer.parseInt(seq));
		param.put("memorandumSeq", memorandumSeq);
		
		return getSqlSession().selectList("memorandumMapper.getMemorandumListIb", param);
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

	public void modifyInvoiceMemorandum(Memorandum memorandum) {
		getSqlSession().update("memorandumMapper.modifyInvoiceMemorandum", memorandum);
	}

	public void deleteMemorandumAllList(MemorandumList memorandumList) {
		getSqlSession().delete("memorandumMapper.deleteMemorandumAllList", memorandumList);
	}

	public List<MemorandumList> getMemorandumAllListIb(String seq) {
		return getSqlSession().selectList("memorandumMapper.getMemroandumAllListIb", seq);
	}

	public void addMemorandumListIb(MemorandumList memorandumList) {
		getSqlSession().insert("memorandumMapper.addMemorandumListIb", memorandumList);			
	}

	public MemorandumList getMemorandumListSelectOneIb(
			MemorandumList memorandumList) {
		return getSqlSession().selectOne("memorandumMapper.getMemorandumListSelectOneIb", memorandumList);
	}

	public void deleteMemorandumIb(Memorandum paramMemorandum) {
		getSqlSession().delete("memorandumMapper.deleteMemorandumIb", paramMemorandum);
		
	}

	public void deleteMemorandumAllListIb(MemorandumList memorandumList) {
		getSqlSession().delete("memorandumMapper.deleteMemorandumAllListIb", memorandumList);		
	}

	public Memorandum getMemorandumIb(Memorandum paramMemorandum) {
		return getSqlSession().selectOne("memorandumMapper.getMemorandumIb", paramMemorandum);
	}
	
	public List<Memorandum> getSitMemorandumIb(Memorandum paramMemorandum) {
		return getSqlSession().selectList("memorandumMapper.getSitiMemorandumIb", paramMemorandum);
	}
	public Memorandum getSitEndMemorandumIb(Memorandum paramMemorandum) {
		return getSqlSession().selectOne("memorandumMapper.getSitEndMemorandumIb", paramMemorandum);
	}
	public void insertMemorandumIb(Memorandum memorandum) {
		getSqlSession().insert("memorandumMapper.insertMemorandumIb", memorandum);
	}

	public void updateMemorandumIb(Memorandum memorandum) {
		getSqlSession().update("memorandumMapper.updateMemorandumIb", memorandum);
	}

	public void insertInvoiceMemorandumIb(Memorandum memorandum) {
		getSqlSession().insert("memorandumMapper.insertInvoiceMemorandumIb", memorandum);
	}
	public void insertMemorandumSitWeight(Memorandum memorandum) {
		getSqlSession().insert("memorandumMapper.insertMemorandumSitWeight", memorandum);
	}
	public void modifyInvoiceMemorandumIb(Memorandum memorandum) {
		getSqlSession().update("memorandumMapper.modifyInvoiceMemorandumIb", memorandum);
	}
	public void modifyInvoiceSitMemorandumIb(Memorandum memorandum) {
		getSqlSession().update("memorandumMapper.modifyInvoiceSitMemorandumIb", memorandum);
	}
}
