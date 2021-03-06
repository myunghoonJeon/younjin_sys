package org.youngjin.net.memorandum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.inbound.InboundDao;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.OutboundDao;

@Service
public class MemorandumService {

	@Resource
	private MemorandumDao memorandumDao;
	
	@Resource
	private OutboundDao outboundDao;
	
	@Resource
	private InboundDao inboundDao;

	public Memorandum getMemorandum(String seq, String type, Integer memorandumSeq, String process) {
		List<Memorandum> testM = new ArrayList<Memorandum>();
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		paramMemorandum.setMemorandumSeq(memorandumSeq);
		
		if("outbound".equals(process)){
			return memorandumDao.getMemorandum(paramMemorandum);
		}
		else if("inbound".equals(process)){
			return memorandumDao.getMemorandumIb(paramMemorandum);
		}
		
		return new Memorandum();
	}

	public void insertMemorandum(Memorandum memorandum, String process) {
		if("outbound".equals(process))
			memorandumDao.insertMemorandum(memorandum);
		else if("inbound".equals(process))
			memorandumDao.insertMemorandumIb(memorandum);
	}

	public Map<String, Memorandum> getMemorandumMap(String seq, Integer memorandumSeq, String process) {
		Map<String, Memorandum> memoMap = new HashMap<String, Memorandum>();
		
		if("outbound".equals(process)){
			List<Memorandum> list = memorandumDao.getMemorandumList(seq, memorandumSeq);
			
			for( Memorandum memorandum : list){
				memoMap.put(memorandum.getType(), memorandum);
			}
		} else if ("inbound".equals(process)){
			List<Memorandum> list = memorandumDao.getMemorandumListIb(seq, memorandumSeq);
			
			for( Memorandum memorandum : list){
				memoMap.put(memorandum.getType(), memorandum);
			}		
		}
		
		return memoMap;
	}

	public void deleteMemorandum(String seq, String type, Integer memorandumSeq, String process) {
		Memorandum paramMemorandum = new Memorandum();
		paramMemorandum.setGblSeq(Integer.parseInt(seq));
		paramMemorandum.setType(type);
		paramMemorandum.setMemorandumSeq(memorandumSeq);
		
		if("outbound".equals(process))
			memorandumDao.deleteMemorandum(paramMemorandum);
		else if("inbound".equals(process))
			memorandumDao.deleteMemorandumIb(paramMemorandum);
	}

	public List<Memorandum> getMemorandumList(String seq, Integer memorandumSeq, String process) {
		if("outbound".equals(process)){
			return memorandumDao.getMemorandumList(seq, memorandumSeq);
		} else if ("inbound".equals(process)){
			return memorandumDao.getMemorandumListIb(seq, memorandumSeq);
		}
		return new ArrayList<Memorandum>();
	}

	public void updateMemorandum(Memorandum memorandum, String process) {
		if("outbound".equals(process))
			memorandumDao.updateMemorandum(memorandum);
		else if("inbound".equals(process))
			memorandumDao.updateMemorandumIb(memorandum);			
	}

	public List<MemorandumList> getMemorandumAllList(String seq, String process) {
		if("outbound".equals(process))	
			return memorandumDao.getMemorandumAllList(seq);
		else if("inbound".equals(process))
			return memorandumDao.getMemorandumAllListIb(seq);
		
		return new ArrayList<MemorandumList>();
	}

	public MemorandumList addMemorandumAndDd619(MemorandumList memorandumList, User user, String process) {
		MemorandumList returnList = new MemorandumList();
		
		if("outbound".equals(process)){
			
			memorandumDao.addMemorandumList(memorandumList);
			
			GBL gbl = outboundDao.getGbl(memorandumList.getGblSeq());
			
			Dd619 dd619 = setDd619(gbl, memorandumList);
			
			outboundDao.insertDd619(dd619);
			
			returnList = memorandumDao.getMemorandumListSelectOne(memorandumList);
		} else if ("inbound".equals(process)){				
			memorandumDao.addMemorandumListIb(memorandumList);		
			
			GBL gbl = inboundDao.getGbl(memorandumList.getGblSeq());
			
			Dd619 dd619 = setDd619(gbl, memorandumList);
			
			inboundDao.insertDd619(dd619);
			
			returnList = memorandumDao.getMemorandumListSelectOneIb(memorandumList);
			
		}
				
		return returnList;
	}

	private Dd619 setDd619(GBL gbl, MemorandumList memorandumList) {
		Dd619 dd619 = new Dd619();
		
		if( gbl.getNo() == null || gbl.getNo().equals("")){
			dd619.setGblNo(gbl.getGblNo());
		} else {
			dd619.setGblNo(gbl.getNo());
		}
		dd619.setGblSeq(memorandumList.getGblSeq());
		dd619.setMemorandumListSeq(memorandumList.getSeq());
		dd619.setName(gbl.getCustomerName());
		dd619.setRank(gbl.getRank());
		dd619.setSsn(gbl.getSsn());		
		dd619.setCarrierShipmentReference(gbl.getScac());
		dd619.setCode(gbl.getCode());
		
		return dd619;
	}

	public void insertInvoiceMemorandum(Memorandum memorandum, String process) {
		if("outbound".equals(process)){
			memorandumDao.insertInvoiceMemorandum(memorandum);
		} else if("inbound".equals(process)){
			memorandumDao.insertInvoiceMemorandumIb(memorandum);			
		}
	}
	public void insertMemorandumSitWeight(Memorandum memorandum, String process) {
		if("outbound".equals(process)){
			memorandumDao.insertInvoiceMemorandum(memorandum);
		} else if("inbound".equals(process)){
			memorandumDao.insertMemorandumSitWeight(memorandum);			
		}
	}
	public void modifyInvoiceMemorandum(Memorandum memorandum, String process) {
		if("outbound".equals(process))
			memorandumDao.modifyInvoiceMemorandum(memorandum);
		else if("inbound".equals(process))
			memorandumDao.modifyInvoiceMemorandumIb(memorandum);			
	}
	public void modifyInvoiceSitMemorandumIb(Memorandum memorandum, String process) {
		if("outbound".equals(process))
			memorandumDao.modifyInvoiceMemorandum(memorandum);
		else if("inbound".equals(process))
			memorandumDao.modifyInvoiceSitMemorandumIb(memorandum);			
	}
	
	public void deleteMemorandumAllList(MemorandumList memorandumList, String process) {
		if("outbound".equals(process)){
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
		} else if("inbound".equals(process)){
			memorandumDao.deleteMemorandumAllListIb(memorandumList);
			
			Integer gblSeq = memorandumList.getGblSeq();
			Integer memorandumSeq = memorandumList.getSeq();
			
			Memorandum memorandum = new Memorandum();
			memorandum.setGblSeq(gblSeq);
			memorandum.setMemorandumSeq(memorandumSeq);
			
			memorandumDao.deleteMemorandumIb(memorandum);
			
			Dd619 dd619 = new Dd619();
			dd619.setGblSeq(gblSeq);
			dd619.setMemorandumListSeq(memorandumSeq);
			
			inboundDao.deleteDd619(dd619);
		}
	}
}
