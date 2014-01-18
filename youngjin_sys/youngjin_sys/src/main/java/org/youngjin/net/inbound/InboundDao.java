package org.youngjin.net.inbound;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;

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
}
