package org.youngjin.net.memorandum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
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

	public Map<String, Memorandum> getMemorandumMap(String seq, Integer memorandumSeq) {
		Map<String, Memorandum> memoMap = new HashMap<String, Memorandum>();
		List<Memorandum> list = memorandumDao.getMemorandumList(seq, memorandumSeq);
		
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

	public List<Memorandum> getMemorandumList(String seq, Integer memorandumSeq) {
		return memorandumDao.getMemorandumList(seq, memorandumSeq);
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
		
		GBL gbl = outboundDao.getGbl(memorandumList.getGblSeq());
		
		Dd619 dd619 = setDd619(gbl, memorandumList);
		
		outboundDao.insertDd619(dd619);
		
		returnList = memorandumDao.getMemorandumListSelectOne(memorandumList);
				
		return returnList;
	}

	private Dd619 setDd619(GBL gbl, MemorandumList memorandumList) {
		Dd619 dd619 = new Dd619();
		dd619.setGblNo(gbl.getNo());
		dd619.setGblSeq(memorandumList.getGblSeq());
		dd619.setMemorandumListSeq(memorandumList.getSeq());
		dd619.setName(gbl.getCustomerName());
		dd619.setRank(gbl.getRank());
		dd619.setSsn(gbl.getSsn());		
		dd619.setCarrierShipmentReference(gbl.getScac());
		dd619.setCode(gbl.getCode());
		
		return dd619;
	}

	public void insertInvoiceMemorandum(Memorandum memorandum) {
		memorandumDao.insertInvoiceMemorandum(memorandum);
	}

	public void modifyInvoiceMemorandum(Memorandum memorandum) {
		memorandumDao.modifyInvoiceMemorandum(memorandum);
	}

	public void deleteMemorandumAllList(MemorandumList memorandumList) {
		memorandumDao.deleteMemorandumAllList(memorandumList);
		
		Integer gblSeq = memorandumList.getGblSeq();
		Integer memorandumSeq = memorandumList.getSeq();
		
		Memorandum memorandum = new Memorandum();
		memorandum.setGblSeq(gblSeq);
		memorandum.setMemorandumSeq(memorandumSeq);
		
		memorandumDao.deleteMemorandum(memorandum);
		
		Dd619 dd619 = new Dd619();
		dd619.setGblSeq(gblSeq);
		dd619.setMemorandumListSeq(memorandumSeq);
		
		outboundDao.deleteDd619(dd619);
	}
}
