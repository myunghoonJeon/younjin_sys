package org.youngjin.net.memorandum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.Dd619;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.OutboundDao;

@Service
public class MemorandumService {

	@Resource
	private MemorandumDao memorandumDao;
	
	@Resource
	private OutboundDao outboundDao;

	public Memorandum getMemorandum(String seq, String type, Integer memorandumSeq) {
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		paramMemorandum.setMemorandumSeq(memorandumSeq);
		
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

	public void deleteMemorandum(String seq, String type, Integer memorandumSeq) {
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		paramMemorandum.setMemorandumSeq(memorandumSeq);
		
		memorandumDao.deleteMemorandum(paramMemorandum);
	}

	public List<Memorandum> getMemorandumList(String seq) {
		return memorandumDao.getMemorandumList(seq);
	}

	public void updateMemorandum(Memorandum memorandum) {
		memorandumDao.updateMemorandum(memorandum);
	}

	public List<MemorandumList> getMemorandumAllList(String seq) {
		return memorandumDao.getMemorandumAllList(seq);
	}

	public MemorandumList addMemorandumAndDd619(MemorandumList memorandumList, User user) {
		MemorandumList returnList = new MemorandumList();
		
		memorandumDao.addMemorandumList(memorandumList);
		
		Dd619 dd619 = new Dd619();
		dd619.setGblSeq(memorandumList.getGblSeq());
		dd619.setMemorandumListSeq(memorandumList.getSeq());
		
		outboundDao.insertDd619(dd619);
		
		returnList = memorandumDao.getMemorandumListSelectOne(memorandumList);
				
		return returnList;
	}
}
