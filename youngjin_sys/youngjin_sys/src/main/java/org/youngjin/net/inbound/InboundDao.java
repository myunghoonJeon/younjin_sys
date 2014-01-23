package org.youngjin.net.inbound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.outbound.Addition;

@Repository
public class InboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getFreightListCount", inboundFilter);
	}

	public List<GBL> getFreightList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getFreightList", inboundFilter);
	}

	public void insertFreight(GBL gbl) {
		getSqlSession().insert("inboundMapper.insertFreight", gbl);
	}

	public void insertFreightStatus(GBL gbl) {
		getSqlSession().insert("inboundMapper.insertFreightStatus", gbl);
	}

	public Boolean checkWeight(Map<String, Integer> param) {
		int checkWeight = getSqlSession().selectOne("inboundMapper.checkWeight", param);
		if(checkWeight > 0){
			return true;
		} else {
			return false;
		}
	}

	public List<WeightIb> getWeightList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getWeightList", seq);
	}
	
	//이전버전

	public GBLStatus getGblProcess(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getGblProcess", seq);
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getGblFileList", seq);
	}

	public List<GBLStatus> getGblStatus(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getGblStatusList", inboundFilter);
	}

	public List<Code> getCarrierList() {
		return getSqlSession().selectList("inboundMapper.getCarrierList");
	}

	public List<Code> getCodeList() {
		return getSqlSession().selectList("inboundMapper.getCodeList");
	}

	public void insertAttachment(GBLAttachment gblAttachment) {
		getSqlSession().insert("inboundMapper.insertAttachment", gblAttachment);		
	}

	public GBL getGbl(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getGbl", seq);
	}
	
	public void insertDd619(Dd619 dd619) {
		getSqlSession().insert("inboundMapper.insertDd619", dd619);
	}

	public void deleteDd619(Dd619 dd619) {
		getSqlSession().delete("inboundMapper.deleteDd619", dd619);
	}

	public List<Dd619> getDd619List(String seq) {
		return getSqlSession().selectList("inboundMapper.getDd619List", seq);
	}

	public void insertWeightAdd(WeightIb weightIb) {
		getSqlSession().insert("inboundMapper.insertWeightAdd", weightIb);
	}

	public int getInboundInvoiceListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getInboundInvoiceListCount", inboundFilter);
	}

	public List<InboundInvoice> getInboundInvoiceList(
			InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceList", inboundFilter);
	}

	public int getCustomInvoiceGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getCustomInvoiceGblListCount", inboundFilter);
	}

	public List<GBL> getCustomInvoiceGblList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getCustomInvoiceGblList", inboundFilter);
	}

	public InboundInvoice getLastInboundInvoiceNo(Integer gblSeq) {
		return getSqlSession().selectOne("inboundMapper.getLastInboundInvoiceNo", gblSeq);
	}

	public Integer inputCustomInboundInvoiceAddSetting(
			InboundInvoice inboundInvoice) {
		getSqlSession().insert("inboundMapper.inputCustomInboundInvoiceAddSetting", inboundInvoice);
		
		return inboundInvoice.getSeq();
	}

	public InboundInvoice getInboundInvoice(Integer inboundInvoiceSeq) {
		return getSqlSession().selectOne("inboundMapper.getInboundInvoice", inboundInvoiceSeq);
	}

	public int checkInboundInvoiceWeight(InboundInvoice inboundInvoice) {
		return getSqlSession().selectOne("inboundMapper.checkInboundInvoiceWeight", inboundInvoice);
	}

	public void inboundInvoiceWeightAdd(Map<String, Integer> weightMap) {
		getSqlSession().insert("inboundMapper.inboundInvoiceWeightAdd", weightMap);
	}

	public int getDeclarationListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getDeclarationListCount", inboundFilter);
	}

	public List<InboundInvoice> getDeclarationList(InboundFilter inboundFilter) {
		return  getSqlSession().selectList("inboundMapper.getDeclarationList", inboundFilter);
	}

	public Dd619 getDd619ListSelectOne(Integer dd619Seq) {
		return getSqlSession().selectOne("inboundMapper.getDd619ListSelectOne", dd619Seq);
	}
	
	public List<Addition> getRemarkValue(String seq, Integer memorandumListSeq) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("gblSeq", Integer.parseInt(seq));
		param.put("memorandumSeq", memorandumListSeq);

		return getSqlSession().selectList("inboundMapper.getRemarkValueList", param);
	}

	public void updateDd619(Dd619 dd619) {
		getSqlSession().update("inboundMapper.updateDd619", dd619);
	}

	public Integer checkAddtionComplete(Addition paramAddition) {
		return getSqlSession().selectOne("inboundMapper.checkAddtionComplete", paramAddition);
	}

	public void additionCompleteUpdate(Addition paramAddition) {
		getSqlSession().update("inboundMapper.additionCompleteUpdate", paramAddition);
	}

	public void additionComplete(Addition paramAddition) {
		getSqlSession().insert("inboundMapper.addtionComplete", paramAddition);
	}

	public List<InboundInvoice> getInboundInvoiceDeclarationList() {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceDeclarationList");
	}

	public void updateGblStatus(Map<String, Integer> statusParam) {
		getSqlSession().update("inboundMapper.updateGblStatus", statusParam);
	}

	public void insertDeclarationList(InboundInvoice declarationInbound) {
		getSqlSession().insert("inboundMapper.inserDeclarationList", declarationInbound);
	}

	public void insertDeclarationListContent(Map<String, String> map) {
		getSqlSession().insert("inboundMapper.insertDeclarationListContent", map);		
	}

	public void updateInboundInvoiceDeclaration(String inboundInvoiceSeq) {
		getSqlSession().update("inboundMapper.updateInboundInvoiceDeclaration", Integer.parseInt(inboundInvoiceSeq));
	}

	public void deleteDeclarationList(Map<String, Integer> inboundInvoiceMap) {
		getSqlSession().delete("inboundMapper.deleteDeclarationList", inboundInvoiceMap);
	}

	public void updateInboundInvoiceDeclarationDelete(
			Map<String, Integer> inboundInvoiceMap) {
		getSqlSession().update("inboundMapper.updateInboundInvoiceDeclarationDelete", inboundInvoiceMap);
	}

	public List<InboundInvoice> getInboundInvoiceListDeclaration(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceListDeclaration", seq);
	}

	public void deleteInboundInvoice(Map<String, Integer> inboundInvoiceMap) {
		getSqlSession().delete("inboundMapper.deleteInboundInvoice", inboundInvoiceMap);
	}

	public void insertOnHandList(OnHandList onHandList) {
		getSqlSession().insert("inboundMapper.insertOnHandList", onHandList);
	}

	public List<InboundInvoice> getInboundInvoiceOnHandList() {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceOnHandList");
	}
	
	public List<InboundInvoice> getOnHandInvoiceListAlreadyInsert(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getOnHandInvoiceListAlreadyInsert", seq);
	}

	public int getOnHandListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getOnHandListCount");
	}

	public List<OnHandList> getOnHandList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getOnHandList", inboundFilter);
	}

	public boolean checkSelectOnHandList(Map<String, Integer> map) {
		int checkCount = getSqlSession().selectOne("inboundMapper.checkSelectOnHandList", map);
		
		if(checkCount > 0){
			return true; 
		} else {
			return false;
		}
	}

	public boolean checkSelectOnHandListContentWeight(Map<String, Integer> map) {
		int checkCount = getSqlSession().selectOne("inboundMapper.checkSelectOnHandListContentWeight", map);
		
		if(checkCount > 0){
			return true; 
		} else {
			return false;
		}
	}

	public void insertOnHandListContentWeight(Map<String, String> paramMap) {
		getSqlSession().insert("inboundMapper.insertOnHandListContentWeight", paramMap);
	}

	public void insertOnHandListContent(OnHandListContent onHandListContent) {
		getSqlSession().insert("inboundMapper.insertOnHandListContent", onHandListContent);
	}

	public List<Integer> getCheckWeightOnHandList(Integer onHandListContentSeq) {
		return getSqlSession().selectList("inboundMapper.getCheckWeightOnHandList", onHandListContentSeq);
	}

	public void deleteOnHandListContentWeight(String onHandListContentSeq) {
		getSqlSession().delete("inboundMapper.deleteOnHandListContentWeight", onHandListContentSeq);		
	}

	public void deleteOnHandListContent(String onHandListContentSeq) {
		getSqlSession().delete("inboundMapper.deleteOnHandListContent", onHandListContentSeq);	
		
	}

	public void updateOnHandListContent(Map<String, String> map) {
		getSqlSession().update("inboundMapper.updateOnHandListContent", map);
	}

	public void deleteOnHandList(Map<String, String> map) {
		getSqlSession().delete("inboundMapper.deleteOnHandList", map);
	}

	public List<Addition> getAddtionList(String seq) {
		return getSqlSession().selectList("inboundMapper.getAdditionList", Integer.parseInt(seq));
	}	
}
