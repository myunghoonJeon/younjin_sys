package org.youngjin.net.outbound;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.code.Code;
import org.youngjin.net.process.GBlock;

@Repository
public class OutboundDao extends SqlSessionDaoSupport {

	public int getGblListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getGblListCount", outboundFilter);
	}

	public List<GBL> getGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getGblList", outboundFilter);
	}

	public GBlock findUsNo(GBlock gBlock) {
		return getSqlSession().selectOne("processMapper.getGBlock", gBlock);
	}

	public void insertGbl(GBL gbl) {
		getSqlSession().insert("outboundMapper.insertGbl", gbl);		
	}

	public List<Code> getCarrierList() {
		return getSqlSession().selectList("outboundMapper.getCarrierList");
	}

	public List<Code> getCodeList() {
		return getSqlSession().selectList("outboundMapper.getCodeList");
	}

	public void insertGblStatus(GBL gbl) {
		getSqlSession().insert("outboundMapper.insertGblStatus", gbl);
		
	}

	public GBLStatus getGblProcess(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getGblProcess", seq);
	}

	public GBL getGbl(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getGbl", seq);
	}

	public void insertAttachment(GBLAttachment gblAttachment) {
		getSqlSession().insert("outboundMapper.insertAttachment", gblAttachment);		
	}

	public List<GBLAttachment> getGblFileList(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getGblFileList", seq);
	}

	public GBLAttachment getFileInfo(Map<String, String> filter) {
		return getSqlSession().selectOne("outboundMapper.getFileInfo", filter);
	}

	public List<GBLStatus> getGblStatus(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getGblStatus", outboundFilter);
	}

	public void insertPreMoveSurvey(PreMoveSurvey preMoveSurvey) {
		getSqlSession().insert("outboundMapper.insertPreMoveSurvey", preMoveSurvey);
	}

	public PreMoveSurvey getPreMoveSurvey(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getPreMoveSurvey", seq);
	}

	public void updatePreMoveSurvey(PreMoveSurvey preMoveSurvey) {
		getSqlSession().update("outboundMapper.updatePreMoveSurvey", preMoveSurvey);
	}

	public List<Dd619> getDd619List(String seq) {
		return getSqlSession().selectList("outboundMapper.getDd619List", seq);
	}

	public void insertDd619(Dd619 dd619) {
		getSqlSession().insert("outboundMapper.insertDd619", dd619);
	}

	public void updateDd619(Dd619 dd619) {
		getSqlSession().update("outboundMapper.updateDd619", dd619);
	}

	public List<Weightcertificate> getWeightcertificateList(String seq) {
		return getSqlSession().selectList("outboundMapper.getWeightcertificateList", seq);
	}

	public void insertWeightcertificate(Weightcertificate weightcertificate) {
		getSqlSession().insert("outboundMapper.insertWeightcertificate", weightcertificate);
	}

	public void updateGblStatus(Map<String, Integer> param) {
		getSqlSession().update("outboundMapper.updateGblStatus", param);		
	}

	public int getTruckListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getTruckCount", outboundFilter);
	}

	public List<TruckManifast> getTruckList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getTruckList", outboundFilter);
	}

	public List<GBL> getTruckGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getTruckGblList", outboundFilter);
	}

	public void insertTurckManifast(TruckManifast truckManifast) {
		getSqlSession().insert("outboundMapper.insertTruckManifast", truckManifast);
	}

	public void updateGbl(GBL gbl) {
		getSqlSession().update("outboundMapper.updateGbl", gbl);
	}

	public void updateWeightcertificate(GBL gbl) {
		getSqlSession().update("outboundMapper.updateWeightCertificateTruck", gbl);
	}

	public int getBookingListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getBookingListCount", outboundFilter);
	}
	
	public List<BookingList> getBookingList(OutboundFilter outboundFilter){
		return getSqlSession().selectList("outboundMapper.getBookingList", outboundFilter);
	}

	public List<GBL> getBookingGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getBookingGblList", outboundFilter);
	}

	public void additionComplete(Addition paramAddition) {
		getSqlSession().insert("outboundMapper.addtionComplete", paramAddition);
	}

	public void insertBookingList(BookingList bookingList) {
		getSqlSession().insert("outboundMapper.insertBookingList", bookingList);
	}
	
}
