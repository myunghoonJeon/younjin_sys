package org.youngjin.net.memorandum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MemorandumService {

	@Resource
	private MemorandumDao memorandumDao;

	public Memorandum getMemorandum(String seq, String type) {
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		
		return memorandumDao.getMemorandum(paramMemorandum);
	}

	public void insertMemorandum(Memorandum memorandum) {
		memorandumDao.insertMemorandum(memorandum);
		
	}

	public Map<String, Memorandum> getMemorandumMap(String seq) {
		Map<String, Memorandum> memoMap = new HashMap<String, Memorandum>();
		List<Memorandum> list = memorandumDao.getMemorandumList(seq);
		
		for( Memorandum memorandum : list){
			memoMap.put(memorandum.getType(), memorandum);
		}
		
		return memoMap;
	}

	public void deleteMemorandum(String seq, String type) {
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		
		memorandumDao.deleteMemorandum(paramMemorandum);
	}

	public List<Memorandum> getMemorandumList(String seq) {
		return memorandumDao.getMemorandumList(seq);
	}
}
