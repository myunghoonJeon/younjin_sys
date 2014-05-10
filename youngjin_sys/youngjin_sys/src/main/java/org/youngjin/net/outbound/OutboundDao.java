package org.youngjin.net.outbound;

import java.awt.Container;
import java.util.ArrayList;
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
	public void updateBookingListUpdate(Map map){
		getSqlSession().update("outboundMapper.updateBookingListUpdate",map);
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
	
	public String getGblTruckDate(Integer seq){
		List<String> list = getSqlSession().selectList("outboundMapper.getGblTruckDate",seq);
		System.out.println("size:"+list.size());
		String str="";
		for(int i=0;i<list.size();i++){
			str+=list.get(i)+" ,";
		}
		System.out.println(str);
		return str;
//		System.out.println(map.get("date").toString());
//		return map.get("date").toString();
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

	public void deleteWeightCertificate(Weightcertificate weightcertificate) {
		getSqlSession().delete("outboundMapper.deleteWeightcertificate", weightcertificate);
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
	
	public List<String> getTruckGblNo(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getTruckGblNo", seq);
	}
	public List<String> getBookingListGblNo(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getBookingListGblNo", seq);
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
	public Integer getBookingListYjCount(){
		int k =  getSqlSession().selectOne("outboundMapper.getBookingListYjCount");
		return k;
	}
	public void insertBookingList(BookingList bookingList) {
		getSqlSession().insert("outboundMapper.insertBookingList", bookingList);
	}

	public List<DeliveryGbl> getBookingListPrint(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getBookingListPrint", seq);
	}

	public BookingList getBookingListOne(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getBookingListOne", seq);
	}

	public List<DeliveryGbl> getTruckManifastPrint(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getTruckManifastPrint", seq);
	}

	public TruckManifast getTruckManifastOne(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getTruckManifastOne", seq);
	}

	public void modifyGbl(GBL gbl) {
		getSqlSession().update("outboundMapper.modifyGbl", gbl);
	}

	public Dd619 getDd619ListSelectOne(Integer dd619Seq) {
		return getSqlSession().selectOne("outboundMapper.getDd619ListSelectOne", dd619Seq);
	}

	public void deleteDd619(Dd619 dd619) {
		getSqlSession().delete("outboundMapper.deleteDd619", dd619);
	}

	public Integer checkAddtionComplete(Addition paramAddition) {
		return getSqlSession().selectOne("outboundMapper.checkAddtionComplete", paramAddition);
	}

	public void additionCompleteUpdate(Addition paramAddition) {
		getSqlSession().update("outboundMapper.additionCompleteUpdate", paramAddition);
	}

	public List<Addition> getAddtionList(String seq) {
		return getSqlSession().selectList("outboundMapper.getAdditionList", Integer.parseInt(seq));
	}

	public List<Addition> getRemarkValue(String seq, Integer memorandumListSeq) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("gblSeq", Integer.parseInt(seq));
		param.put("memorandumSeq", memorandumListSeq);

		return getSqlSession().selectList("outboundMapper.getRemarkValueList", param);
	}

	public Integer getCheckWeightCertificateAndGetSeq(
			Weightcertificate paramWeightcertificate) {
		return getSqlSession().selectOne("outboundMapper.getCheckWeightCertificateAndGetSeq", paramWeightcertificate);
	}

	public void updateWeightcertificateNormal(Weightcertificate paramWeightcertificate) {
		getSqlSession().update("outboundMapper.updateWeightCertificate", paramWeightcertificate);
	}

	public void insertGblStatusCopy(GBLStatus gblStatus) {
		getSqlSession().insert("outboundMapper.insertGblStatusCopied", gblStatus);
	}

	public void deleteTruckGbl(Integer seq) {
		getSqlSession().update("outboundMapper.deleteTruckGbl", seq);
	}
	public void deleteBookingListGbl(Integer seq) {
		getSqlSession().update("outboundMapper.deleteBookingListGbl", seq);
	}
	public void passBookingListGbl(Integer seq) {
		getSqlSession().update("outboundMapper.passBookingListGbl", seq);
	}
	public void deleteTruckManifast(Integer seq) {
		getSqlSession().delete("outboundMapper.deleteTruckManifast", seq);
	}

	public void deleteGblStatusByTruckManifast(Integer seq) {
		getSqlSession().update("outboundMapper.deleteGblStatusByTruckManifast", seq);
	}
	
	public void deleteGblStatusByBookingList(Integer seq) {
		getSqlSession().update("outboundMapper.deleteGblStatusByBookingList", seq);
	}	
	
	public void passBookingList(String seq){
		getSqlSession().update("outboundMapper.passBookinglist",seq);
	}
	
	public void deleteWeightCertificateByTruckManiafast(Integer seq) {
		getSqlSession().update("outboundMapper.deleteWeightCertificateByTruckManifast", seq);
	}
	
	public void deleteWeightCertificateByBookingList(Integer seq) {
		getSqlSession().update("outboundMapper.deleteWeightCertificateByBookingList", seq);
	}
	
	public void passWeightCertificateByBookingList(Integer seq){
		getSqlSession().update("outboundMapper.passWeightCertificateByBookingList",seq);
	}
	
	public void mergeGblWeight(GBL gbl) {
		getSqlSession().delete("outboundMapper.mergeGblWeightCertificate", gbl);
	}

	public void mergeGblStatus(GBL gbl) {
		getSqlSession().delete("outboundMapper.mergeGblStatus", gbl);
	}

	public void mergeGbl(GBL gbl) {
		getSqlSession().delete("outboundMapper.mergeGbl", gbl);
	}

	public void setSeperatedFlag(GBL gbl) {
		getSqlSession().update("outboundMapper.setSeperatedFlag", gbl);
	}

	public int checkSeperateComplete(String gblNo) {
		return getSqlSession().selectOne("outboundMapper.checkSeperateComplete", gblNo);
	}

	public void updateGblStatusByGblNo(String gblNo) {
		getSqlSession().update("outboundMapper.updateGblStatusByGblNo", gblNo);
	}

	public List<Container> getContainerList() {
		return getSqlSession().selectList("outboundMapper.getContainerManageList");
	}

	public void minusContainerByStatus(String status) {
		getSqlSession().update("outboundMapper.minusContainerByStatus", status);
	}

	public Weightcertificate getWeightcertificateOne(
			Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getWeightcertificateOne", seq);
	}

	public void plusContainerByStatus(String status) {
		getSqlSession().update("outboundMapper.plusContainerByStatus", status);
		
	}

	public List<String> getTcmdGblSeqList(int seq) {
		return getSqlSession().selectList("outboundMapper.getTcmdGblSeqList",seq);
	}
	
	
	
	public List<Tcmd> getTcmdList() {
		return getSqlSession().selectList("outboundMapper.getTcmdList");
	}
	public List<GBL> getTcmdGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getTcmdGblList", outboundFilter);
	}

	public int getTcmdGblListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getTcmdGblListCount", outboundFilter);
	}
	public int getTcmdListCount(OutboundFilter outboundFilter) {
		return getSqlSession().selectOne("outboundMapper.getTcmdListCount", outboundFilter);
	}
	public int getHouseListCount(OutboundFilter outboundFilter){
		return getSqlSession().selectOne("outboundMapper.getHouseListCount", outboundFilter);
	}
	public void insertTcmd(Tcmd tcmd) {
		getSqlSession().insert("outboundMapper.insertTcmd", tcmd);
	}

	public void insertTcmdGblList(Map<String, Integer> seqMap) {
		getSqlSession().insert("outboundMapper.insertTcmdGblList", seqMap);
	}

	public Tcmd getTcmdContent(Integer seq) {
		return getSqlSession().selectOne("outboundMapper.getTcmdContent", seq);
	}

	public List<GBL> getTcmdContentGblList(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getTcmdContentGblList", seq);
	}

	public GBL getMilSvcGbl(String seq, String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("milSvc", type);
		
		return getSqlSession().selectOne("outboundMapper.getMilSvcGbl", map);
	}

	public void updateTcmdGbl(Map<String, String> map) {
		getSqlSession().update("outboundMapper.updateTcmdGbl", map);
	}

	public void updateTcmd(Map<String, String> map) {
		getSqlSession().update("outboundMapper.updateTcmd", map);
	}

	public void deleteGBL(GBL gbl) {
		getSqlSession().delete("outboundMapper.deleteGbl", gbl);
	}
	
	public void deleteTcmdList(Integer seq){
		getSqlSession().delete("outboundMapper.deleteTcmdList", seq);
	}
	public void deleteTcmdGblList(Integer seq){
		getSqlSession().delete("outboundMapper.deleteTcmdGblList", seq);
	}
	
	public void deleteBookingList(Map<String, String> bookingSeq) {
		getSqlSession().delete("outboundMapper.deleteBookingList", bookingSeq);
	}
	
//	public void deleteBookingList(Map<String, String> bookingSeq) {
//		getSqlSession().delete("outboundMapper.deleteBookingList", bookingSeq);
//	}
	
	public List<GBL> getHouseGblList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getHouseGblList", outboundFilter);
	}

	public void houseMergeGblWeight(GBL gbl) {
		getSqlSession().delete("outboundMapper.houseMergeGblWeightCertificate", gbl);		
	}

	public void houseMergeGblStatus(GBL gbl) {
		getSqlSession().delete("outboundMapper.houseMergeGblStatus", gbl);
		
	}

	public void houseMergeGbl(GBL gbl) {
		getSqlSession().delete("outboundMapper.houseMergeGbl", gbl);		
	}

	public void setHouseSeperatedFlag(GBL gbl) {
		getSqlSession().update("outboundMapper.setHouseSeperatedFlag", gbl);
	}

	public void insertHouse(House house) {
		getSqlSession().insert("outboundMapper.insertHouse", house);
	}

	public List<House> getHouseList(OutboundFilter outboundFilter) {
		return getSqlSession().selectList("outboundMapper.getHouseList", outboundFilter);
	}

	public void deleteUpdateHouseWeight(Integer seq) {
		getSqlSession().update("outboundMapper.deleteUpdateHouseWeight", seq);
	}

	public void deleteHouse(Integer seq) {
		getSqlSession().delete("outboundMapper.deleteHouse", seq);
	}

	public List<GBL> getGblListHouse(String seq) {
		return getSqlSession().selectList("outboundMapper.getGblListHouse", seq);
	}

	public House getHouse(String seq) {
		return getSqlSession().selectOne("outboundMapper.getHouse", Integer.parseInt(seq));
	}

	
	//gbl delete
	public void deleteGblStatus(Integer seq) {
		getSqlSession().delete("outboundMapper.deleteGblStatus", seq);
	}

	public List<Integer> getTcmdContentGblListByGblSeq(Integer seq) {
		return getSqlSession().selectList("outboundMapper.getTcmdContentGblListByGblSeq", seq);
	}

	public void deleteTcmd(Integer tcmdSeq) {
		getSqlSession().delete("outboundMapper.deleteTcmd", tcmdSeq);		
	}

	public GBL getGblInfoByNo(GBL gbl) {
		return getSqlSession().selectOne("outboundMapper.getGblInfoByNo", gbl);
	}
}
