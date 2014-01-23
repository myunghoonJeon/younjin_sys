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
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.login.User;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.upload.UploadService;

@Service
public class InboundService {
	
	@Resource
	private InboundDao inboundDao;
	
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
		String [] remark = weightIb.getRemark().split(",", count);
		Integer gblSeq = weightIb.getGblSeq();
		
		for( int i = 0 ; i < count ; i ++ ){
			WeightIb weightParam = new WeightIb();
			weightParam.setPiece(pcs[i]);
			weightParam.setType(type[i]);
			weightParam.setGross(gross[i]);
			weightParam.setGrossKg(grossKg[i]);
			weightParam.setTare(tare[i]);
			weightParam.setNet(net[i]);
			weightParam.setCuft(cuft[i]);
			weightParam.setRemark(remark[i]);
			weightParam.setGblSeq(gblSeq);
			
			inboundDao.insertWeightAdd(weightParam);
		}
		
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
		return inboundDao.getGbl(seq);
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

	public Boolean checkInboundInvoiceWeight(InboundInvoice inboundInvoice) {
		int checkInboundInvoice = inboundDao.checkInboundInvoiceWeight(inboundInvoice);
		
		if(checkInboundInvoice > 0){
			return true;
		}
		
		return false;
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

		String[] invoiceMemorandumType = dd619.getInvoiceMemorandumType()
				.split(",", dd619.getCount());
		String[] invoiceMemorandumValue = dd619.getInvoiceMemorandumValue()
				.split(",", dd619.getCount());

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

	public void inboundInvoiceDelete(Map<String, Integer> inboundInvoiceMap) {
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

	public List<OnHandList> getOnHandList(InboundFilter inboundFilter) {
		return inboundDao.getOnHandList(inboundFilter);
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
}
