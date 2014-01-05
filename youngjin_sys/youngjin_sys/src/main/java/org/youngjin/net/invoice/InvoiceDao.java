package org.youngjin.net.invoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.outbound.Weightcertificate;

@Repository
public class InvoiceDao extends SqlSessionDaoSupport {

	public Integer getSitListCount() {
		return getSqlSession().selectOne("invoiceMapper.getSitListCount");
	}

	public void insertNewYearSIT(List<Rate> rateList) {
		Map<String, List<Rate>> param = new HashMap<String, List<Rate>>();
		param.put("rateList", rateList);
		
		getSqlSession().insert("invoiceMapper.insertNewYearSIT", param);
	}

	public List<Rate> getSitList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.getSitList", rate);
	}

	public Integer getOtherListCount() {
		return getSqlSession().selectOne("invoiceMapper.getOtherListCount");
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
		return getSqlSession().selectOne("invoiceMapper.getBasicRate", rate);
	}

	public Rate getContainerRate(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getContainerRate", rate);
	}

	public Rate getOther(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getOther", rate);
	}

	public int getEtcCheck(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getEtcCheck", rate);
	}

	public void etcInsert(Rate rate) {
		getSqlSession().insert("invoiceMapper.etcInsert", rate);		
	}

	public List<Rate> getEtcList(Rate rate) {
		return getSqlSession().selectList("invoiceMapper.etcList", rate);
	}

	public Rate getEtc(String title, String pud) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("title", title);
		param.put("pud", pud);
		
		return getSqlSession().selectOne("invoiceMapper.getEtc", param);
	}

	public void etcUpdate(Rate rate) {
		getSqlSession().update("invoiceMapper.etcRateUpdate", rate);
	}

	public Rate getSit(Rate rate) {
		return getSqlSession().selectOne("invoiceMapper.getSit", rate);
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

	public void updateInvoiceGbl(InvoiceGbl invoiceGbl) {
		getSqlSession().update("invoiceMapper.updateInvoiceGbl", invoiceGbl);
	}

	public void checkAndUpdateInvoice(Integer invoiceSeq) {
		getSqlSession().update("invoiceMapper.checkAndUpdateInvoice", invoiceSeq);
	}
	
}
