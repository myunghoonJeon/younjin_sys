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
	
	public List<MonthlyReport> getMontlyList(Map map){
		return getSqlSession().selectList("inboundMapper.getMonthlyList", map);
	}
	public List<MonthlyReport> getMontlyListOther(Map map){
		return getSqlSession().selectList("inboundMapper.getMonthlyListOther", map);
	}
	public List<MonthlyReport> getMontlyListYongsan(Map map){
		return getSqlSession().selectList("inboundMapper.getMonthlyListYongsan", map);
	}
	public int getMonthlySitCount(Map map){
		System.out.println("SIT COUNT CALL");
		return getSqlSession().selectOne("inboundMapper.getMonthlySitCount", map);
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

	public WeightIb getWeight(WeightIb weightParam) {
		return getSqlSession().selectOne("inboundMapper.getWeight", weightParam);
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
	
	public Integer inputCustomInboundInvoiceAddSettingCodeTJ(InboundInvoice inboundInvoice){
		getSqlSession().insert("inboundMapper.inputCustomInboundInvoiceAddSettingCodeTJ", inboundInvoice);
		return inboundInvoice.getSeq();
	}
	
	public InboundInvoice getInboundInvoice(Integer inboundInvoiceSeq) {
		return getSqlSession().selectOne("inboundMapper.getInboundInvoice", inboundInvoiceSeq);
	}

	public InboundInvoice getAddInboundInvoice(Integer inboundInvoiceSeq) {
		return getSqlSession().selectOne("inboundMapper.getAddInboundInvoice", inboundInvoiceSeq);
	}

	public int checkInboundInvoiceWeight(InboundInvoice inboundInvoice) {
		return getSqlSession().selectOne("inboundMapper.checkInboundInvoiceWeight", inboundInvoice);
	}
	public void passInboundInvoiceWeightAdd(Map<String,String> map){
		getSqlSession().insert("inboundMapper.passInboundInvoiceWeightAdd", map);
	}
	public void inboundInvoiceWeightAdd(Map<String, Integer> weightMap) {
		getSqlSession().insert("inboundMapper.inboundInvoiceWeightAdd", weightMap);
	}
	
	public void inboundInvoiceWeightAddCodeTJ(Map<String, Integer> weightMap) {
		getSqlSession().insert("inboundMapper.inboundInvoiceWeightAddCodeTJ", weightMap);
	}
	public int getDeclarationListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getDeclarationListCount", inboundFilter);
	}
	public int getDeclarationGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getDeclarationGblListCount", inboundFilter);
	}
	public List<DeclarationList> getDeclarationList(InboundFilter inboundFilter) {
		List<DeclarationList> dl = getSqlSession().selectList("inboundMapper.getDeclarationList", inboundFilter);
		return  dl;
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

	public List<InboundInvoice> getInboundInvoiceDeclarationList(InboundFilter inboundfilter) {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceDeclarationList",inboundfilter);
	}

	public void updateGblStatus(Map<String, Integer> statusParam) {
		System.out.println("[[[[[[[[[[[change WEIGHT STATE : GBL SEQ ["+statusParam.get("invoiceGblseq")+"]]]]]]]]]]]]]");
		getSqlSession().update("inboundMapper.updateGblStatus", statusParam);
	}
	
	public void updateInvoiceGblStatus(String gblNo) {
		System.out.println("[[[[[[[[[[[change INVOICE STATE : GBL NO ["+gblNo+"]]]]]]]]]]]]]");
		getSqlSession().update("inboundMapper.updateInvoiceGblStatus", gblNo);
	}
	public void updateInvoiceGblRemoveStatus(Map map) {
		System.out.println("[[[[[[[[[[[ INVOICE STATE REMOVE : GBL SEQ ["+map.get("seq")+"]]]]]]]]]]]]]");
		getSqlSession().update("inboundMapper.updateGblInvoiceRemoveStatus", map);
	}
	public void updateCustomStatus(Map<String, Integer> statusParam){
		System.out.println("[[[[[[[[[[[change CUSTOM STATE : GBL SEQ ["+statusParam.get("custom")+"]]]]]]]]]]]]]");
		getSqlSession().update("inboundMapper.updateCustomStatus", statusParam);
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

	public void deleteInboundInvoice(Map<String, String> inboundInvoiceMap) {
		getSqlSession().delete("inboundMapper.deleteInboundInvoice", inboundInvoiceMap);
	}

	public void insertOnHandList(OnHandList onHandList) {
		getSqlSession().insert("inboundMapper.insertOnHandList", onHandList);
	}

	public List<InboundInvoice> getInboundInvoiceOnHandList() {
		return getSqlSession().selectList("inboundMapper.getInboundInvoiceOnHandList");
	}
	public List<String> getOnhandListCustomerList(Integer seq){
		return getSqlSession().selectList("inboundMapper.getOnhandListCustomerList", seq);
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

	public List<OnHandList> getOnHandListContentForm(Integer seq){ 
		return getSqlSession().selectList("inboundMapper.getOnHandListForm", seq);
	}
	public OnhandSum getOnhandListSum(Integer seq){
		return getSqlSession().selectOne("inboundMapper.getOnhandSum",seq);
	}
	public void onHandListContentByUpdate(OnHandListContent onHandListContent) {
		getSqlSession().update("inboundMapper.onHandListContentByUpdate", onHandListContent);
	}

	public int getTruckGblListCount(InboundFilter inboundFilter) {
		return getSqlSession().selectOne("inboundMapper.getTruckGblListCount");
	}

	public void insertTruckManifast(TruckManifast truckManifast) {
		getSqlSession().insert("inboundMapper.insertTruckManifast", truckManifast);
//		getSqlSession().insert("inboundMapper.insertGblTruckdate",truckManifast);
	}
	public void insertGblTruckdate(Map<String,String> map){
		getSqlSession().insert("inboundMapper.insertGblTruckDate", map);
	}
	public void insertTruckManifastOnHand(TruckManifast truckManifast) {
		getSqlSession().insert("inboundMapper.insertTruckManifastOnHand", truckManifast);
	}

	public List<GBL> getOnHandGBLList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getOnHandGblList", inboundFilter);
	}

	public List<TruckManifast> getTruckManifastList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getTruckManifastList", inboundFilter);
	}

	public void deleteTruckManifast(Map<String, String> resultMap) {
		getSqlSession().delete("inboundMapper.deleteTruckManifast", resultMap);
	}

	public void updateWeight(WeightIb weightParam) {
		getSqlSession().update("inboundMapper.updateWeight", weightParam);
	}

	public List<Reweight> getReweightList(InboundFilter inboundFilter) {
		return getSqlSession().selectList("inboundMapper.getReweightList", inboundFilter);
	}

	public Reweight getReweight(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getReweight", seq);
	}

	public List<GBL> getReweightGblList() {
		return getSqlSession().selectList("inboundMapper.getReweightGblList");
	}

	public void insertReweight(Reweight reweight) {
		getSqlSession().insert("inboundMapper.insertReweight", reweight);
	}

	public void insertReweightContent(ReweightContent reweightContent) {
		getSqlSession().insert("inboundMapper.insertReweightContent", reweightContent);
	}

	public void reweightDelete(Reweight reweight) {
		getSqlSession().delete("inboundMapper.deleteReweight", reweight);
	}

	public void updateReweightCheck(Map<String, Integer> map) {
		getSqlSession().update("inboundMapper.updateReweightCheck", map);
	}

	public void updateFreight(GBL gbl) {
		getSqlSession().update("inboundMapper.updateFreight", gbl);
	}

	public List<Integer> getTruckManifastOnHandContentListSeqList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getTruckManifastOnHandContentListSeqList", seq);
	}

	public GBL getOnhandListContent(Integer onHandListContentSeq) {
		return getSqlSession().selectOne("inboundMapper.getOnHandListContent", onHandListContentSeq);
	}

	public TruckManifast getTruckBasicInfo(Integer seq) {
		return getSqlSession().selectOne("inboundMapper.getTruckBasicInfo", seq);
	}

	public Integer getDeclarationSeq(String seq) {
		return getSqlSession().selectOne("inboundMapper.getDeclarationSeq", seq);
	}

	public void deleteDeclarationListBySeq(Integer declarationSeq) {
		getSqlSession().delete("inboundMapper.deleteDeclarationListBySeq", declarationSeq);
		
	}

	public List<ReweightContent> getReweightContentList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getReweightContentList", seq);
	}

	public OnHandList getOnHandListOne(Integer onHandListContentSeq) {
		return getSqlSession().selectOne("inboundMapper.getOnHandListOne", onHandListContentSeq);
	}

	public void updateGblEtc(GBL gbl) {
		getSqlSession().update("inboundMapper.updateGblEtc", gbl);
	}

	public GBL getGblInfoByNo(GBL gbl) {
		return getSqlSession().selectOne("inboundMapper.getGblInfoByNo", gbl);
	}

	public List<Integer> getDeclarationSeqList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getDeclarationSeqList", seq);
	}

	public List<Integer> getOnHandListSeqList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getOnHandListSeqList", seq);
	}

	public List<Integer> getReweightSeqList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getReweightSeqList", seq);
	}

	public void deleteReweight(Reweight reweight) {
		getSqlSession().delete("inboundMapper.deleteReweight", reweight);		
	}

	public List<Integer> getTruckManifastSeqList(Integer seq) {
		return getSqlSession().selectList("inboundMapper.getTruckManifastSeqList", seq);
	}

	public void deleteGblStatus(Integer seq) {
		getSqlSession().delete("inboundMapper.deleteGblStatus", seq);		
	}

	public void deleteGBL(Integer seq) {
		getSqlSession().delete("inboundMapper.deleteGbl", seq);		
	}

	public void deleteTruckManifastEmptyTruck() {
		getSqlSession().delete("inboundMapper.deleteTruckManifastEmptyTruck");		
	}

	public void deleteEmptyOnHandList() {
		getSqlSession().delete("inboundMapper.deleteEmptyOnHandList");		
	}
}
