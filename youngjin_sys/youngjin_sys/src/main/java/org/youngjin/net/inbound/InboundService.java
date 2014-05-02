package org.youngjin.net.inbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.youngjin.net.Dd619;
import org.youngjin.net.GBL;
import org.youngjin.net.GBLAttachment;
import org.youngjin.net.GBLStatus;
import org.youngjin.net.OnhandSum;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.invoice.InvoiceService;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.upload.UploadService;

@Service
public class InboundService {
	
	@Resource
	private InboundDao inboundDao;
	
	@Resource
	private InvoiceService invoiceService;
	
	@Resource
	private UploadService uploadService;
	
	@Resource
	private CodeDao codeDao;
	
	public int getFreightListCount(InboundFilter inboundFilter, User user) {	
		if(!"LEVEL4".equals(user.getAuthStr()) && !"LEVEL3".equals(user.getAuthStr()) && !"LEVEL2".equals(user.getAuthStr())){
			inboundFilter.setArea("0" + user.getArea().toString());
		}
		
		return inboundDao.getGblListCount(inboundFilter);
	}

	public List<GBL> getFreightList(InboundFilter inboundFilter, User user) {
		if(!"LEVEL4".equals(user.getAuthStr()) && !"LEVEL3".equals(user.getAuthStr()) && !"LEVEL2".equals(user.getAuthStr())){
			inboundFilter.setArea("0" + user.getArea().toString());
		}
		
		return inboundDao.getFreightList(inboundFilter);
	}

	/*public GBlock findUsNo(GBlock gBlock) {
		return inboundDao.findUsNo(gBlock);
	}
	*/
	public void insertFreight(GBL gbl) {
		inboundDao.insertFreight(gbl);
		
		inboundDao.insertFreightStatus(gbl);
	}
	/*
	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();		
		filterMap.put("branchList", branchList);
		
		List<Code> carrierList = inboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);
		
		List<Code> codeList = inboundDao.getCodeList();
		filterMap.put("codeList", codeList);
		
		
		return filterMap;
	}*/

	public Map<String, String> getGblStatus(InboundFilter inboundFilter) {
		Map<String, String> gblStatusMap = new HashMap<String, String>();
		
		List<GBLStatus> gblStatusList = inboundDao.getGblStatus(inboundFilter);
		
		for ( GBLStatus gblStatus : gblStatusList ){
				gblStatusMap.put(gblStatus.getNo(), gblStatus.getInboundCurrentState());
		}
		
		return gblStatusMap;
	}

	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();		
		filterMap.put("branchList", branchList);
		
		List<Code> carrierList = inboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);
		
		List<Code> codeList = inboundDao.getCodeList();
		filterMap.put("codeList", codeList);
		
		
		return filterMap;
	}
	
	public Boolean checkWeight(Map<String, Integer> param) {
		return inboundDao.checkWeight(param);
	}

	public List<WeightIb> getWeightList(Integer seq) {
		return inboundDao.getWeightList(seq);
	}

	public Map<Integer, Boolean> getCheckWeightList(Integer onHandListContentSeq) {
		Map<Integer, Boolean> checkMap = new HashMap<Integer, Boolean>();
		
		List<Integer> tempList = inboundDao.getCheckWeightOnHandList(onHandListContentSeq);
		
		for(Integer weightSeq : tempList){
			checkMap.put(weightSeq, true);
		}
		
		return checkMap;
	}

	public void insertWeightAdd(WeightIb weightIb) {
		String [] pcs = weightIb.getPiece().split(",");

		int count = pcs.length;		
		
		String [] type = weightIb.getType().split(",", count);
		String [] gross = weightIb.getGross().split(",", count);
		String [] grossKg = weightIb.getGrossKg().split(",", count);
		String [] tare = weightIb.getTare().split(",", count);
		String [] net = weightIb.getNet().split(",", count);
		String [] cuft = weightIb.getCuft().split(",", count);
		String reweight = weightIb.getReweight();
		String [] remark = weightIb.getRemark().split(",", count);
		Integer gblSeq = weightIb.getGblSeq();
		
		Integer totalGross = 0;
		Integer totalTare = 0;
		Integer totalNet = 0;
		Integer totalCuft = 0;
		Integer totalPcs = 0;
		
		for( int i = 0 ; i < count ; i ++ ){
			WeightIb weightParam = new WeightIb();
			weightParam.setPiece(pcs[i]);
			weightParam.setType(type[i]);
			weightParam.setGross(gross[i]);
			weightParam.setGrossKg(grossKg[i]);
			weightParam.setTare(tare[i]);
			weightParam.setNet(net[i]);
			weightParam.setCuft(cuft[i]);
			weightParam.setReweight(reweight);
			weightParam.setRemark(remark[i]);
			weightParam.setGblSeq(gblSeq);
			
			WeightIb weight = inboundDao.getWeight(weightParam);
			if(weight != null){
				inboundDao.updateWeight(weightParam);
			} else {			
				inboundDao.insertWeightAdd(weightParam);
			}
			
			totalGross += Integer.parseInt(gross[i]);
			totalNet += Integer.parseInt(net[i]);
			totalCuft += Integer.parseInt(cuft[i]);
		}
		
		GBL gbl = new GBL();
		gbl.setSeq(gblSeq);
		gbl.setGrossWeight(totalGross.toString());
		gbl.setNetWeight(totalNet.toString());
		gbl.setCuft(totalCuft.toString());
		gbl.setTotalPcs(Integer.toString(count));
		
		inboundDao.updateGblEtc(gbl);
		
		Map<String, Integer> statusParam = new HashMap<String, Integer>();
		statusParam.put("gblSeq", gblSeq);
		statusParam.put("weight", 1);
		inboundDao.updateGblStatus(statusParam);
	}	

	public int getCustomInvoiceGblListCount(InboundFilter inboundFilter) {
		return inboundDao.getCustomInvoiceGblListCount(inboundFilter);
	}	
	
	//이전버전
	
	public List<GBLAttachment> getGblFileList(Integer seq) {
		return inboundDao.getGblFileList(seq);
	}

	public GBLStatus getGblProcessAndUpload(Integer seq) {
		return inboundDao.getGblProcess(seq); 
	}

	public void insertGblFile(GBL gbl){
		insertAttachments(gbl);
	}	
	
	private void insertAttachments(GBL gbl) {
		List<GBLAttachment> files = gblAttachments(gbl.getAttachments());
		gbl.setAttachmentList(files);
		gbl.setGblNo(getGbl(gbl.getSeq()).getGblNo());
		
		for (GBLAttachment gblAttachment : files) {
			CommonsMultipartFile uploadedFile = gblAttachment.getCommonsMultipartFile();
			if (uploadedFile.isEmpty()) continue;
			String filePath = uploadService.transferFile(uploadedFile, "inbound", gbl.getGblNo());
			
			gblAttachment.setFilePath(filePath);	
			gblAttachment.setGblNo(gbl.getGblNo());
			gblAttachment.setGblFileNo(gbl.getGblFileNo());
			
			inboundDao.insertAttachment(gblAttachment);
		}
	}
	
	public static List<GBLAttachment> gblAttachments(CommonsMultipartFile attachments[]) {
		ArrayList<GBLAttachment> list = new ArrayList<GBLAttachment>();
		try {
			if (attachments == null) throw new Exception();
			for (CommonsMultipartFile commonsMultipartFile : attachments) {
				list.add(new GBLAttachment(commonsMultipartFile));
			}			
		} catch (Exception e) {
		}
		return list;
	}
	
	public GBL getGbl(Integer seq) {
		GBL gbl = inboundDao.getGbl(seq);
		
		List<WeightIb> weightList = inboundDao.getWeightList(seq);
		Integer totalGross = 0;
		Integer totalNet = 0;
		Integer totalCuft = 0;
		Integer totalPcs = 0;
		for(WeightIb weightIb : weightList){
			totalGross += Integer.parseInt(weightIb.getGross());
			totalNet += Integer.parseInt(weightIb.getNet());
			totalCuft += Integer.parseInt(weightIb.getCuft());
			totalPcs += 1;
		}
		
		gbl.setGrossWeight(totalGross.toString());
		gbl.setNetWeight(totalNet.toString());
		gbl.setCuft(totalCuft.toString());
		gbl.setPcs(totalPcs.toString());
		
		return gbl;
	}

	public List<Dd619> getDd619List(String seq) {
		return inboundDao.getDd619List(seq);
	}

	public int getInboundInvoiceListCount(InboundFilter inboundFilter) {
		return inboundDao.getInboundInvoiceListCount(inboundFilter);
	}

	public List<InboundInvoice> getInboundInvoiceList(InboundFilter inboundFilter) {
		return inboundDao.getInboundInvoiceList(inboundFilter);
	}

	public List<GBL> getCustomInvoiceGblList(InboundFilter inboundFilter) {
		return inboundDao.getCustomInvoiceGblList(inboundFilter);
	}

	public Map<String, String> getInboundInvoiceSettingMap(Integer gblSeq) {
		Map<String, String> settingMap = new HashMap<String, String>();
		GBL gbl = inboundDao.getGbl(gblSeq);
		
		settingMap.put("gblNo", gbl.getGblNo());
		
		InboundInvoice lastInboundInvoice = inboundDao.getLastInboundInvoiceNo(gblSeq);
		
		String inboundInvoiceNo = "";
		if(lastInboundInvoice.getInboundInvoiceNo() == null){
			inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-00001";
		} else {
			if(lastInboundInvoice.getInboundInvoiceNo().length() == 1){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-0000" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 2){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-000" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 3){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-00" + lastInboundInvoice.getInboundInvoiceNo();
			} else if(lastInboundInvoice.getInboundInvoiceNo().length() == 4){
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + "-0" + lastInboundInvoice.getInboundInvoiceNo();
			} else {
				inboundInvoiceNo = lastInboundInvoice.getInvoiceDate() + lastInboundInvoice.getInboundInvoiceNo();
			}
		}
		
		settingMap.put("inboundInvoiceNo", inboundInvoiceNo);
		
		return settingMap;
	}

	public Integer inputCustomInboundInvoiceAddSetting(
			InboundInvoice inboundInvoice) {
		String inboundInvoiceNo = inboundInvoice.getInboundInvoiceNo().substring(3, 8);
		inboundInvoice.setInboundInvoiceNo(inboundInvoiceNo);
		
		return inboundDao.inputCustomInboundInvoiceAddSetting(inboundInvoice);
	}

	public InboundInvoice getInboundInvoiceBasicInfo(Integer inboundInvoiceSeq) {
		return inboundDao.getInboundInvoice(inboundInvoiceSeq);
	}
	
	public InboundInvoice getInboundInvoiceAddBasicInfo(
			Integer inboundInvoiceSeq) {
		return inboundDao.getAddInboundInvoice(inboundInvoiceSeq);
	}

	public Boolean checkInboundInvoiceWeight(InboundInvoice inboundInvoice) {
		int checkInboundInvoice = inboundDao.checkInboundInvoiceWeight(inboundInvoice);
		
		if(checkInboundInvoice > 0){
			return true;
		}
		
		return false;
	}
	public void passInboundInvoiceWeightAdd(Map<String,String> map){
		inboundDao.passInboundInvoiceWeightAdd(map);
	}
	public void inboundInvoiceWeightAdd(
			Map<String, String> inboundInvoiceWeightMap) {
		String [] weightSeqList = inboundInvoiceWeightMap.get("weightSeqCommaList").split(",");
		String inboundInvoiceSeq = inboundInvoiceWeightMap.get("inboundInvoiceSeq");
		
		for(String weightSeq : weightSeqList){
			Map<String, Integer> weightMap = new HashMap<String, Integer>();
			weightMap.put("weightSeq", Integer.parseInt(weightSeq));
			weightMap.put("inboundInvoiceSeq", Integer.parseInt(inboundInvoiceSeq));
			
			inboundDao.inboundInvoiceWeightAdd(weightMap);
		}
	}

	public int getDeclarationListCount(InboundFilter inboundFilter) {
		return inboundDao.getDeclarationListCount(inboundFilter);
	}

	public List<InboundInvoice> getDeclarationList(InboundFilter inboundFilter) {
		
		return inboundDao.getDeclarationList(inboundFilter);
	}

	public InboundInvoice getDeclaration(Integer seq) {
		InboundInvoice inboundInvoice = new InboundInvoice();
		inboundInvoice.setSeq(seq);
		
		return null;//inboundDao.getDeclarationList(inboundFilter).get(0);
	}

	public List<InboundInvoice> getInboundInvoiceDeclarationList() {
		return inboundDao.getInboundInvoiceDeclarationList();
	}

	public Dd619 getDd619ListSelectOne(Integer dd619Seq) {
		return inboundDao.getDd619ListSelectOne(dd619Seq);
	}
	
	public Map<String, Double> getRemarkValue(String seq,
			Integer memorandumListSeq) {
		Map<String, Double> remarkValue = new HashMap<String, Double>();
		List<Addition> additionList = inboundDao.getRemarkValue(seq,
				memorandumListSeq);

		for (Addition addition : additionList) {
			remarkValue.put(addition.getTitle(), (addition.getCost() == null || addition.getCost().equals("")) ? Double.parseDouble(addition.getPrice()) : addition.getCost());
		}

		return remarkValue;
	}

	public void modifyDd619(Dd619 dd619) {
		inboundDao.updateDd619(dd619);

		Addition paramAddition = new Addition();
		paramAddition.setDd619Seq(dd619.getSeq());
		paramAddition.setGblSeq(dd619.getGblSeq());
		paramAddition.setMemorandumSeq(dd619.getMemorandumListSeq());
		
		Integer additionCheck = inboundDao.checkAddtionComplete(paramAddition);

		String[] invoiceMemorandumType = (dd619.getCount() != 0) ? dd619.getInvoiceMemorandumType()
				.split(",", dd619.getCount()) : null;
		String[] invoiceMemorandumValue = (dd619.getCount() != 0) ? dd619.getInvoiceMemorandumValue()
				.split(",", dd619.getCount()) : null;

		for (int i = 0; i < dd619.getCount(); i++) {
			paramAddition.setTitle(invoiceMemorandumType[i]);
			paramAddition.setPrice(invoiceMemorandumValue[i]);

			if (additionCheck > 0) {
				inboundDao.additionCompleteUpdate(paramAddition);
			} else {
				inboundDao.additionComplete(paramAddition);
			}
		}
	}

	public void declarationListSelectAdd(Map<String, String> inboundInvoiceMap) {
		String [] inboundInvoiceSeqList = inboundInvoiceMap.get("inboundInvoiceCommaList").split(",");
		
		InboundInvoice declarationInbound = new InboundInvoice();
		inboundDao.insertDeclarationList(declarationInbound);
		
		for(String inboundInvoiceSeq : inboundInvoiceSeqList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("declarationListSeq", declarationInbound.getSeq().toString());
			map.put("inboundInvoiceSeq", inboundInvoiceSeq);
			
			inboundDao.insertDeclarationListContent(map);
			
			inboundDao.updateInboundInvoiceDeclaration(inboundInvoiceSeq);
		}
		
	}

	public void declarationListDelete(Map<String, Integer> inboundInvoiceMap) {
		inboundDao.updateInboundInvoiceDeclarationDelete(inboundInvoiceMap);
		
		inboundDao.deleteDeclarationList(inboundInvoiceMap);
	}

	public List<InboundInvoice> getInboundInvoiceListDeclaration(Integer seq) {
		return inboundDao.getInboundInvoiceListDeclaration(seq);
	}

	public void inboundInvoiceDelete(Map<String, String> inboundInvoiceMap) {		
		String seq = inboundInvoiceMap.get("seq");
		
		updateStatusCustom(Integer.parseInt(seq), "delete");
		
		Integer declarationSeq = inboundDao.getDeclarationSeq(seq);
		
		inboundDao.deleteDeclarationListBySeq(declarationSeq);
		
		inboundDao.deleteInboundInvoice(inboundInvoiceMap);
	}

	public Integer onHandListAdd(OnHandList onHandList) {
		inboundDao.insertOnHandList(onHandList);		
		
		return onHandList.getSeq();
	}

	public List<InboundInvoice> getInboundInvoiceOnHandList(Integer seq) {
		List<InboundInvoice> onHandInvoiceList = inboundDao.getInboundInvoiceOnHandList();
		
		List<InboundInvoice> onHandInvoiceListAlreadyInsert = inboundDao.getOnHandInvoiceListAlreadyInsert(seq);
		
		for(InboundInvoice inboundInvoice : onHandInvoiceListAlreadyInsert){
			onHandInvoiceList.add(inboundInvoice);
		}
		
		return onHandInvoiceList;
	}

	public int getOnHandListCount(InboundFilter inboundFilter) {
		return inboundDao.getOnHandListCount(inboundFilter);
	}
	public int getTruckManifastListCount(InboundFilter inboundFilter){
		return inboundDao.getTruckGblListCount(inboundFilter);
	}
	
	public List<OnHandList> getOnHandList(InboundFilter inboundFilter) {
		return inboundDao.getOnHandList(inboundFilter);
	}

	public List<GBL> getOnHandGBLList(InboundFilter inboundFilter) {
		return inboundDao.getOnHandGBLList(inboundFilter);
	}

	public boolean checkSelectonHandList(Map<String, Integer> map) {
		return inboundDao.checkSelectOnHandList(map);
	}

	public boolean checkSelectonHandListContentWeight(Map<String, Integer> map) {
		return inboundDao.checkSelectOnHandListContentWeight(map);
	}

	public void onHandListContentWeightAdd(Map<String, String> map) {
		String [] weightSeqCommaList = map.get("weightSeqCommaList").split(",");
		
		String onHandListContentSeq = map.get("onHandListContentSeq");
		
		inboundDao.deleteOnHandListContent(onHandListContentSeq);
		
		OnHandListContent onHandListContent = new OnHandListContent();
		onHandListContent.setGblSeq(Integer.parseInt(map.get("gblSeq")));
		onHandListContent.setOnHandListSeq(Integer.parseInt(map.get("onHandListSeq")));
		
		OnHandList onHandList = inboundDao.getOnHandListOne(onHandListContent.getOnHandListSeq());
		GBL gbl = new GBL();
		gbl.setSeq(onHandListContent.getGblSeq());
		gbl.setOnHandDate(onHandList.getOnHandDate());
		
		inboundDao.updateGblEtc(gbl);
		
		inboundDao.insertOnHandListContent(onHandListContent);
		
		for(String weightSeqComma : weightSeqCommaList){
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("onHandListContentSeq", onHandListContent.getSeq().toString());
			paramMap.put("weightSeq", weightSeqComma);
			
			inboundDao.insertOnHandListContentWeight(paramMap);
		}				
	}

	public void onHandListContentSelectAdd(Map<String, String> map) {
		inboundDao.updateOnHandListContent(map);
		
		Map<String, Integer> statusParam = new HashMap<String, Integer>();
		statusParam.put("seq", Integer.parseInt(map.get("seq")));
		statusParam.put("onHandList", 1);
		
		inboundDao.updateGblStatus(statusParam);
	}

	public void onHandListDelete(Map<String, String> map) {
		
		Map<String, Integer> statusParam = new HashMap<String, Integer>();
		statusParam.put("seq", Integer.parseInt(map.get("seq")));
		statusParam.put("onHandList", 0);
		
		inboundDao.updateGblStatus(statusParam);
		
		inboundDao.deleteOnHandList(map);
	}

	public void updateStatusCustom(Integer seq) {
		Map<String, Integer> statusParam = new HashMap<String, Integer>();
		statusParam.put("seq", seq);
		statusParam.put("custom", 1);
		
		inboundDao.updateGblStatus(statusParam);
	}
	
	public void updateStatusCustom(Integer seq, String delete) {
		Map<String, Integer> statusParam = new HashMap<String, Integer>();
		statusParam.put("seq", seq);
		if(delete.equals("delete"))
			statusParam.put("custom", 0);
		else
			statusParam.put("custom", 1);			
		
		inboundDao.updateGblStatus(statusParam);
	}

	public List<Addition> getAddtionList(String seq) {
		return inboundDao.getAddtionList(seq);
	}

	public void additionComplete(Addition addition) {
		String titleList[] = addition.getTitle().split(",");
		String priceList[] = addition.getPrice().split(",");

		int titleListSize = titleList.length;
		int priceListSize = priceList.length;

		if (titleListSize != priceListSize) {
			if (titleListSize > priceListSize) {
				titleList[titleListSize - 1] = null;
			} else {
				priceList[priceListSize - 1] = null;
			}
		}

		Addition paramAddition = new Addition();
		paramAddition.setGblSeq(addition.getGblSeq());

		for (int i = 0; i < titleListSize; i++) {
			paramAddition.setTitle(titleList[i]);
			paramAddition.setCost(Double.parseDouble(priceList[i]));

			Integer checkAddition = inboundDao
					.checkAddtionComplete(paramAddition);

			if (checkAddition == 0) {
				inboundDao.additionComplete(paramAddition);
			} else {
				inboundDao.additionCompleteUpdate(paramAddition);
			}
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("delivery", 1);
		map.put("seq", addition.getGblSeq());
		inboundDao.updateGblStatus(map);
	}

	public List<OnHandList> getOnHandListContentListForm(Integer seq) {
		return inboundDao.getOnHandListContentForm(seq);
	}
	
	public OnhandSum getOnhandListSum(Integer seq){
		return inboundDao.getOnhandListSum(seq);
	}
	public void onHandListContentByUpdate(OnHandListContent onHandListContent) {
		inboundDao.onHandListContentByUpdate(onHandListContent);
	}

	public void insertTruckManifast(Map<String, String> resultMap) {
		String truckManifastDate = resultMap.get("truckManifastDate");
		String area = resultMap.get("area");
		String [] onHandListSeqList = resultMap.get("onHandSeq").split(",");
		String [] gblSeqList = resultMap.get("gblSeqList").split(",");
		
		TruckManifast truckManifast = new TruckManifast();
		truckManifast.setTruckManifastDate(truckManifastDate);
		truckManifast.setArea(area);
		
		inboundDao.insertTruckManifast(truckManifast);
		
		for(String gblSeq : gblSeqList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("gblSeq", gblSeq);
			map.put("truckDate", truckManifastDate);
			System.out.println("gblSeq : "+gblSeq);
			inboundDao.insertGblTruckdate(map);
		}
		
		for(String onHandSeq : onHandListSeqList){
			truckManifast.setOnHandListContentSeq(Integer.parseInt(onHandSeq));
			inboundDao.insertTruckManifastOnHand(truckManifast);
		}
	}

	public List<TruckManifast> getTruckManifastList(InboundFilter inboundFilter) {
		return inboundDao.getTruckManifastList(inboundFilter);
	}

	public void deleteTruckManifast(Map<String, String> resultMap) {
		inboundDao.deleteTruckManifast(resultMap);
	}

	public List<Reweight> getReWeightList(InboundFilter inboundFilter) {
		return inboundDao.getReweightList(inboundFilter);
	}

	public List<ReweightContent> getReweightGblList() {
		List<GBL> tempReweightGblList = inboundDao.getReweightGblList();
		List<ReweightContent> reweightGblList = new ArrayList<ReweightContent>();
		
		for( GBL gbl : tempReweightGblList){
			String [] reweightArray = gbl.getWeightIb().getReweight().split("/", 4);
			
			ReweightContent reweightContent = new ReweightContent();
			reweightContent.setDeliDate(reweightArray[3]);
			reweightContent.setOriginGblock(gbl.getGbloc());
			reweightContent.setCode(gbl.getCode());
			reweightContent.setScacCode(gbl.getTsp());
			reweightContent.setGblNo(gbl.getGblNo());
			reweightContent.setFullName(gbl.getShipperName());
			reweightContent.setoWt(gbl.getWeightIb().getGross());
			reweightContent.setrWt(reweightArray[0]);
			reweightContent.setDentn(gbl.getAreaLocal()); // 일단 빈칸
			reweightContent.setGblSeq(gbl.getSeq());
			if(reweightArray.length > 3){
				reweightContent.setRateGbl31("$" + ((reweightArray[2].equals("")) ? "0" : reweightArray[2]));
			} else {
				reweightContent.setRateGbl31("");
			}
			
			reweightGblList.add(reweightContent);
		}
		
		return reweightGblList;
		
	}

	public void reweightAdd(Map<String, String> paramMap) {
		String reweightSubject = paramMap.get("reweightSubject");
		String [] gblSeqList = paramMap.get("gblSeqCommaList").split(",");
		
		Reweight reweight = new Reweight();
		reweight.setReweightName(reweightSubject);
		
		inboundDao.insertReweight(reweight);
		
		for(String gblSeq : gblSeqList){
			ReweightContent reweightContent = new ReweightContent();
			reweightContent.setGblSeq(Integer.parseInt(gblSeq));
			reweightContent.setReweightSeq(reweight.getSeq());
			
			inboundDao.insertReweightContent(reweightContent);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("seq", Integer.parseInt(gblSeq));
			map.put("check", 1);
			
			inboundDao.updateReweightCheck(map);
		}
		
	}

	public void reweightDelete(Reweight reweight) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("reweightSeq", reweight.getSeq());
		map.put("check", 0);
		
		inboundDao.updateReweightCheck(map);
		
		inboundDao.reweightDelete(reweight);
	}

	public WeightIb getWeightTotal(String seq) {
		List<WeightIb> weightList = getWeightList(Integer.parseInt(seq));
		
		WeightIb weightIb = new WeightIb();
		Double totalGross = 0.0;
		Double totalTare = 0.0;
		Double totalNet = 0.0;
		for(WeightIb tempWeight : weightList){
			totalGross += Double.parseDouble(tempWeight.getGross());
			totalTare += Double.parseDouble(tempWeight.getTare());
		}
		
		totalNet = totalGross - totalTare;
		
		weightIb.setGross(totalGross.toString());
		weightIb.setTare(totalTare.toString());
		weightIb.setNet(totalNet.toString());
		
		if(weightList.get(0).getReweight() != null){
			String [] reweight = weightList.get(0).getReweight().split("/", 3);
			
			if(reweight.length == 3){
				weightIb.setReGross(reweight[0]);
				weightIb.setReTare(reweight[1]);
				
				Double net = Double.parseDouble(reweight[0]) - Double.parseDouble(reweight[1]);
				weightIb.setReNet(net.toString());
			}
		}
		return weightIb;
	}

	public void updateFreight(GBL gbl) {
		inboundDao.updateFreight(gbl);
	}

	public List<GBL> getTruckManifastContentList(Integer seq) {
		List<Integer> onHandListContentSeqList = inboundDao.getTruckManifastOnHandContentListSeqList(seq);
		
		List<GBL> onHandListContent = new ArrayList<GBL>();
		
		for(Integer onHandListContentSeq : onHandListContentSeqList){
			onHandListContent.add(inboundDao.getOnhandListContent(onHandListContentSeq));
		}
		
		return onHandListContent;
	}

	public TruckManifast getTruckBasicInfo(Integer seq) {
		return inboundDao.getTruckBasicInfo(seq);
	}

	public Reweight getReweight(Integer seq) {
		return inboundDao.getReweight(seq);
	}

	public List<ReweightContent> getReweightListBySeq(Integer seq) {
		List<ReweightContent> reweightList = new ArrayList<ReweightContent>();
		
		List<ReweightContent> reweightContentList = inboundDao.getReweightContentList(seq);
		
		for(ReweightContent reweightContent : reweightContentList){
			GBL gbl = inboundDao.getGbl(reweightContent.getGblSeq());
			
			ReweightContent reweightContentPure = new ReweightContent();
			
			reweightContentPure.setDentn(gbl.getAreaLocal());
			reweightContentPure.setFullName(gbl.getShipperName());
			reweightContentPure.setGblNo(gbl.getGblNo());
			reweightContentPure.setOriginGblock(gbl.getGbloc());
			reweightContentPure.setCode(gbl.getCode());
			reweightContentPure.setScacCode(gbl.getTsp());
			
			List<WeightIb> weightIbList = inboundDao.getWeightList(reweightContent.getGblSeq());
			
			Integer totalGross = 0;
			for(WeightIb weight : weightIbList){
				totalGross += Integer.parseInt(weight.getGross());
			}
			
			String [] reweight = weightIbList.get(0).getReweight().split("/", 4);
			
			if(reweight.length > 3){
				reweightContentPure.setDeliDate(reweight[3]);
				reweightContentPure.setRateGbl31(reweight[2]);
				reweightContentPure.setrWt(reweight[0]);
			}
			
			reweightContentPure.setoWt(totalGross.toString());
			
			reweightList.add(reweightContentPure);
		}
		
		return reweightList;
	}

	public GBL getGblInfoByNo(GBL gbl) {
		return inboundDao.getGblInfoByNo(gbl);
	}

	public void deleteGBL(GBL gbl) {		
		List<Integer> declarationListSeq = inboundDao.getDeclarationSeqList(gbl.getSeq());	
		for(Integer seq : declarationListSeq){
			inboundDao.deleteDeclarationListBySeq(seq);
		}
		
		List<Integer> getInvoiceSeqList = invoiceService.getInvoiceSeqListByGblSeq(gbl.getSeq());
		
		for(Integer invoiceSeq : getInvoiceSeqList){
			invoiceService.deleteInvoice(invoiceSeq, "inbound");
		}
		
		List<Integer> truckManifastSeqList = inboundDao.getTruckManifastSeqList(gbl.getSeq());
		
		for(Integer seq : truckManifastSeqList){
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("truckSeq", seq.toString());			
			inboundDao.deleteTruckManifast(resultMap);
		}
		
		List<Integer> getOnHandListSeqList = inboundDao.getOnHandListSeqList(gbl.getSeq());
		
		for(Integer seq : getOnHandListSeqList){
			Map<String, String> map = new HashMap<String, String>();
			map.put("seq", seq.toString());
			inboundDao.deleteOnHandList(map);
		}
		
		List<Integer> reweightSeqList = inboundDao.getReweightSeqList(gbl.getSeq());
		
		for(Integer seq : reweightSeqList){
			Reweight reweight = new Reweight();
			reweight.setSeq(seq);
			inboundDao.deleteReweight(reweight);
		}
		
		inboundDao.deleteGblStatus(gbl.getSeq());
		
		inboundDao.deleteGBL(gbl.getSeq());
	}

	public void deleteTruckManifastEmptyTruck() {
		inboundDao.deleteTruckManifastEmptyTruck();
	}

	public void deleteEmptyOnHandList() {
		inboundDao.deleteEmptyOnHandList();		
	}
}
