package org.youngjin.net.invoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.GBL;
import org.youngjin.net.code.Code;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.Weightcertificate;

@Repository
public class InvoiceDao extends SqlSessionDaoSupport {

	public Integer getSitListCount(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getSitListCount",rate);
	}

	public void insertNewYearSIT(List<Rate> rateList) {
		Map<String, List<Rate>> param = new HashMap<String, List<Rate>>();
		param.put("rateList", rateList);
		getSqlSession().insert("invoiceMapper.insertNewYearSIT", param);
	}

	public List<Rate> getSitList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.getSitList", rate);
	}

	public Integer getOtherListCount(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getOtherListCount",rate);
	}

	public List<Rate> getOtherList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.getOtherList", rate);
	}

	public void insertNewYearOther(List<Rate> rateList) {
		Map<String, List<Rate>> param = new HashMap<String, List<Rate>>();
		param.put("rateList", rateList);
				
		getSqlSession().insert("invoiceMapper.insertNewYearOther", param);
	}

	public void basicUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.basicUpdate", rate);
		
	}

	public int getBasicRateCheck(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getBasicRateCheck", rate);
	}

	public void basicInsert(Rate rate) {
		getSqlSession().insert("invoiceMapper.basicInsert", rate);
		
	}

	public List<Rate> getBasicRateList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.getBasicRateList", rate);
	}

	public int getContainerCheck(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getContainerCheck", rate);
	}

	public void containerInsert(Rate rate) {
		getSqlSession().insert("invoiceMapper.containerInsert", rate);
	}

	public void containerUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.containerUpdate", rate);
	}

	public List<Rate> getContainerList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.getContainerRateList", rate);
	}

	public void sitUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.sitUpdate", rate);
	}

	public void otherUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.otherUpdate", rate);
	}

	public int getInvoiceListCount(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceListCount", invoiceFilter);
	}

	public List<Invoice> getInvoiceList(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceList", invoiceFilter); 
	}

	public void insertInvoice(Invoice invoice) {
		getSqlSession().insert("invoiceMapper.insertInvoice", invoice);
	}

	public List<InvoiceGbl> getSettingGblList(Invoice invoice) {
		return getSqlSession().selectList("invoiceMapper.getSettingGblList", invoice);
	}

	public void insertInvoiceGbl(InvoiceGbl invoiceGbl) {
		getSqlSession().insert("invoiceMapper.insertInvoiceGbl", invoiceGbl);
	}

	public Invoice getInvoice(Invoice invoice) {
		return getSqlSession().selectOne("invoiceMapper.getInvoice", invoice);
	}

	public List<InvoiceGbl> getInvoiceGblList(Integer seq) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceGblList", seq);
	}

	public List<InvoiceGbl> getInvoiceGblListIb(Integer seq) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceGblListIb", seq);
	}

	public void deleteInvoice(Invoice invoice) {
		getSqlSession().delete("invoiceMapper.deleteInvoice", invoice);
	}

	public List<InvoiceGbl> getInvoiceGblListByInvoice(Invoice invoice) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceGblListbyInvoice", invoice);
	}

	public Integer getInvoiceGblContentCount(Integer invoiceGblSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceGblContentCount", invoiceGblSeq);
	}

	public Weightcertificate getTotalWeightCertificate(Integer seq) {
		return getSqlSession().selectOne("invoiceMapper.getTotalWeightCertificate", seq);
	}

	public Rate getBasicRate(Rate rate) {
		System.out.println("[[[[[[[ getBasicRate ]]]]]]]]]]]]");
		Rate returnRate = getSqlSession().selectOne("invoiceMapper.getBasicRate", rate);
		
		if(returnRate == null){
			System.out.println("NULL RATE RETURN");
			return new Rate();
		}
		
		return returnRate;
	}

	public Rate getContainerRate(Rate rate) {
		Rate returnRate = getSqlSession().selectOne("invoiceMapper.getContainerRate", rate);
		
		if(returnRate == null)
			return new Rate();
		
		return returnRate;
	}

	public Rate getOther(Rate rate) {
		System.out.println("==========================================================");
		System.out.println("==========================================================");
		Rate returnRate = getSqlSession().selectOne("invoiceMapper.getOther", rate);
		
		if(returnRate == null)
			return new Rate();
		
		return returnRate;
	}

	public int getEtcCheck(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getEtcCheck", rate);
	}

	public void etcInsert(Rate rate) {
		getSqlSession().insert("invoiceMapper.etcInsert", rate);		
	}
	
	public void etcInsert2(Map map) {
		getSqlSession().insert("invoiceMapper.etcInsert2", map);	
	}
	
	public List<Rate> getEtcList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.etcList", rate);
	}

	public Rate getEtc(String title, String pud) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("title", title);
		param.put("pud", pud);

		Rate returnRate = getSqlSession().selectOne("invoiceMapper.getEtc", param);
		
		if(returnRate == null)
			return new Rate();
		
		return returnRate;
	}

	public void etcUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.etcRateUpate", rate);
	}

	public Rate getSit(Rate rate) {
		Rate returnRate = getSqlSession().selectOne("invoiceMapper.getSit", rate);
		
		if(returnRate == null)
			return new Rate();
		
		return returnRate;
	}

	public Integer checkInvoiceContent(InvoiceGblContent invoiceGblContent) {
		return getSqlSession().selectOne("invoiceMapper.checkInvoiceContent", invoiceGblContent);
	}

	public void updateInvoiceGblContent(InvoiceGblContent invoiceGblContent) {
		getSqlSession().update("invoiceMapper.updateInvoiceGblContent", invoiceGblContent);
	}
	
	public void insertInvoiceGblContent(InvoiceGblContent invoiceGblContent) {
		getSqlSession().insert("invoiceMapper.insertInvoiceGblContent", invoiceGblContent);
		
	}
	public String getLongcarryAmount(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getLongcarryAmount", gblSeq);
	}
	public String getTerminationReason(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getTerminationReason", gblSeq);
	}
	public String getExtraPickupCheck(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getExtraPickupCheck", gblSeq);
	}
	public String getSitNumber(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getSitNumber", gblSeq);
	}
	public String getSitStartDay(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getSitStartDay", gblSeq);
	}
	public String getSitEndDay(int gblSeq){
		return getSqlSession().selectOne("invoiceMapper.getSitEndDay", gblSeq);
	}
	public void updateInvoiceGbl(InvoiceGbl invoiceGbl) {
		getSqlSession().update("invoiceMapper.updateInvoiceGbl", invoiceGbl);
	}

	public void checkAndUpdateInvoice(Integer invoiceSeq) {
		getSqlSession().update("invoiceMapper.checkAndUpdateInvoice", invoiceSeq);
	}

	public InvoiceGbl getInvoiceGblcontentInfo(Integer invoiceGblSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceGblContentInfo", invoiceGblSeq);
	}

	public int getInvoiceSettingGblListCount(InvoiceGblFilter invoiceGblFilter) {
		return getSqlSession().selectOne("invoiceMapper.getInvoicesttingGblListCount", invoiceGblFilter);
	}

	public int getInvoiceSettingGblListIbCount(InvoiceGblFilter invoiceGblFilter) {
		System.out.println("test"+invoiceGblFilter.getArea());
		return getSqlSession().selectOne("invoiceMapper.getInvoiceSettingGblListIbCount", invoiceGblFilter);
	}

	public List<GBL> getInvoiceSettingGblList(InvoiceGblFilter invoiceGblFilter) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceSettingGblList", invoiceGblFilter);
	}

	public List<GBL> getInvoiceSettingGblListIb(InvoiceGblFilter invoiceGblFilter) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceSettingGblListIb", invoiceGblFilter);
	}	

	public InvoiceGbl getInvoiceSettingGblListContent(String gblSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceSettingGblContent", gblSeq);
	}

	public InvoiceGbl getInvoiceSettingGblListContentIb(String gblSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceSettingGblContentIb", gblSeq);
	}

	public String checkTodayInvoiceNo() {
		return getSqlSession().selectOne("invoiceMapper.checkTodayInvoiceNo");
	}

	public List<Code> getCarrierList(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectList("invoiceMapper.getCarrierList", invoiceFilter);
	}

	public int getInvoiceCollectionListCount(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceCollectionListCount", invoiceFilter);
	}

	public List<Invoice> getInvoiceCollectionList(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectList("invoiceMapper.getInvoicecollectionList", invoiceFilter);
	}
	
	public String getSumInvoiceGblCollectionFlowAmount(int seq){
		return getSqlSession().selectOne("invoiceMapper.getSumInvoiceGblCollectionFlowAmount",seq);
	}
	public void updateInvoiceCollectionStatusComplete(int seq){
		getSqlSession().update("invoiceMapper.updateInvoiceCollectionStatusComplete",seq);
	}
	public void updateInvoiceCollectionStatusPending(int seq){
		getSqlSession().update("invoiceMapper.updateInvoiceCollectionStatusPending",seq);
	}
	public String getInvoiceGblCollectionAmount(int seq){
		return getSqlSession().selectOne("invoiceMapper.getInvoiceGblCollectionAmount",seq);
	}
	public List<InvoiceCollection> getInvoiceCollectionListAndFlow(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceCollectionListAndFlow", invoiceFilter);
	}
	public InvoiceCollection getInvoiceCollectionListAndFlow2(InvoiceFilter invoiceFilter) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceCollectionListAndFlow2", invoiceFilter);
	}
	public void inputCollectionNet(InvoiceCollection invoiceCollection) {
		getSqlSession().insert("invoiceMapper.inputCollectionNet", invoiceCollection);
	}
	public void inputCollectionFlow(InvoiceCollectionFlow invoiceCollectionFlow) {
		getSqlSession().insert("invoiceMapper.inputCollectionFlow", invoiceCollectionFlow);
	}
	public InvoiceCollection checkAndGetCollectionSeq(String invoiceSeq) {
		return getSqlSession().selectOne("invoiceMapper.checkAndGetCollectionSeq", invoiceSeq);
	}

	public void updateCollectionNet(InvoiceCollection invoiceCollection) {
		getSqlSession().update("invoiceMapper.updateCollectionNet", invoiceCollection);
	}

	public void deleteInvoiceCollection(String collectionSeq) {
		getSqlSession().delete("invoiceMapper.deleteInvoiceCollection", collectionSeq);
	}
	public void removeRateYear(Map<String,String> map) {
		getSqlSession().delete("invoiceMapper.removeYearListTable", map);
		getSqlSession().delete("invoiceMapper.removeOtherRate", map);
		getSqlSession().delete("invoiceMapper.removeSitRate", map);
		getSqlSession().delete("invoiceMapper.removeEtcRate", map);
		getSqlSession().delete("invoiceMapper.removeRate", map);
	}
	public void deleteInvoiceCollection(Invoice invoice) {
		getSqlSession().delete("invoiceMapper.deleteInvoiceCollectionByInvoiceSeq", invoice);
	}

	public void deleteInvoiceCollectionFlow(String flowSeq) {
		getSqlSession().delete("invoiceMapper.deleteInvoiceCollectionFlow", flowSeq);
	}

	public List<InvoiceCollection> getInvoiceCollectionGblListAndFlow(Integer seq) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceCollectionGblListAndFlow", seq);
	}

	public void updateGblCollectionNet(InvoiceCollection invoiceCollection) {
		getSqlSession().update("invoiceMapper.updateGblCollectionNet", invoiceCollection);		
	}

	public void inputGblCollectionNet(InvoiceCollection invoiceCollection) {
		getSqlSession().insert("invoiceMapper.inputGblCollectionNet", invoiceCollection);
	}

	public InvoiceCollection checkAndGetGblCollectionSeq(String invoiceGblSeq) {
		return getSqlSession().selectOne("invoiceMapper.checkAndGetGblCollectionSeq", invoiceGblSeq);
	}

	public void inputGblCollectionFlow(
			InvoiceCollectionFlow invoiceGblCollectionFlow) {
		getSqlSession().insert("invoiceMapper.inputGblCollectionFlow", invoiceGblCollectionFlow);		
	}

	public void deleteGblInvoiceCollection(String collectionSeq) {
		getSqlSession().delete("invoiceMapper.deleteGblInvoiceCollection", collectionSeq);
	}

	public void deleteGblInvoiceCollectionFlow(String flowSeq) {
		getSqlSession().delete("invoiceMapper.deleteGblInvoiceCollectionFlow", flowSeq);
	}

	public void invoiceCollectionRemarkInput(
			Map<String, String> invoiceCollection) {
		getSqlSession().update("invoiceMapper.invoiceCollectionRemarkInput", invoiceCollection);
	}

	public Integer getInvoiceCollectionSeq(Integer invoiceSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceCollectionSeq", invoiceSeq);
	}

	public Invoice getInvoiceByInvoiceSeq(Integer seq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoice", seq);
	}

	public List<String> getYearList() {
		return getSqlSession().selectList("invoiceMapper.getYearList");
	}
	
	public void addRateYear(String year){
		Map<String,String> map = new HashMap<String,String>();
		map.put("year", year);
		getSqlSession().insert("invoiceMapper.addRateYear", map);
	}
	
	public InvoiceGbl getInvoiceGblcontentInfoIb(Integer invoiceGblSeq) {
		return getSqlSession().selectOne("invoiceMapper.getInvoiceGblContentInfoIb", invoiceGblSeq);
	}

	public List<Integer> getInvoiceSeqListByGblSeq(Integer seq) {
		return getSqlSession().selectList("invoiceMapper.getInvoiceSeqListByGblSeq", seq);
	}
}
