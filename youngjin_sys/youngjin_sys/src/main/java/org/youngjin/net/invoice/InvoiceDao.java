package org.youngjin.net.invoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

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
	
}
