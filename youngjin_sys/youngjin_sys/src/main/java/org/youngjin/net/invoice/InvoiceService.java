package org.youngjin.net.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.GBL;
import org.youngjin.net.basic.BasicService;
import org.youngjin.net.code.Code;
import org.youngjin.net.code.CodeDao;
import org.youngjin.net.inbound.InboundDao;
import org.youngjin.net.inbound.WeightIb;
import org.youngjin.net.memorandum.Memorandum;
import org.youngjin.net.memorandum.MemorandumDao;
import org.youngjin.net.outbound.Addition;
import org.youngjin.net.outbound.OutboundDao;
import org.youngjin.net.outbound.OutboundFilter;
import org.youngjin.net.outbound.Weightcertificate;
import org.youngjin.net.util.DateUtil;

@Service
public class InvoiceService {

	@Resource
	private InvoiceDao invoiceDao;

	@Resource
	private OutboundDao outboundDao;

	@Resource
	private InboundDao inboundDao;

	@Resource
	private MemorandumDao memorandumDao;

	@Resource
	private CodeDao codeDao;
	
	@Resource
	private BasicService basicService;

	public void checkNewYearRate() {
		// SIT charge Rate schedule check
		Integer SITcheck = invoiceDao.getSitListCount();
		if (SITcheck == 0) {
			List<Rate> sitRateList = new ArrayList<Rate>();

			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C",
					"UB", "OUTBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate(
					"30mile 이하  - IT13 item 521K (minimum per shipment)", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate(
					"30mile 이상  - IT13 item 521J (minimum per shipment)", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG",
					"OUTBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D",
					"HHG", "OUTBOUND"));

			sitRateList.add(new Rate("SIT-FIRST DAY - IT13 item 519A", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 519C",
					"UB", "INBOUND"));
			sitRateList.add(new Rate("SIT-DELIVERY CHARGE & ADM FEE", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("30mile 이하 - IT13 item 521I", "UB",
					"INBOUND"));
			sitRateList.add(new Rate(
					"30mile 이하  - IT13 item 521K (minimum per shipment)", "UB",
					"INBOUND"));
			sitRateList.add(new Rate(
					"30mile 이상  - IT13 item 521J (minimum per shipment)", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("ADM FEE - IT13 item 521L", "UB",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-FIRST DAY -IT13 item 518C", "HHG",
					"INBOUND"));
			sitRateList.add(new Rate("SIT-EACH ADDITIONALDAY - IT13 item 518D",
					"HHG", "INBOUND"));

			invoiceDao.insertNewYearSIT(sitRateList);
		}

		// Other charge Schedule check
		Integer otherCheck = invoiceDao.getOtherListCount();
		if (otherCheck == 0) {
			List<Rate> otherRateList = new ArrayList<Rate>();

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222",
					"UB", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B",
					"UB", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A",
					"UB", "OUTBOUND"));
			otherRateList
					.add(new Rate("termination discount", "UB", "OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"UB", "OUTBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("termination discount", "HHG",
					"OUTBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"HHG", "OUTBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG",
					"OUTBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG",
					"OUTBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE - IT13 item 2222",
					"UB", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505B",
					"UB", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 523A",
					"UB", "INBOUND"));
			otherRateList
					.add(new Rate("termination discount", "UB", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"UB", "INBOUND"));

			otherRateList.add(new Rate("EXTRA PICKUP CHARGE -IT13 item 1111",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("REWEIGHT CHARGE - IT13 item 505A",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("TERMINATION CHARGE - IT13 item 522A",
					"HHG", "INBOUND"));
			otherRateList
					.add(new Rate("termination discount", "HHG", "INBOUND"));
			otherRateList.add(new Rate("DIVERSION CHARGE - IT13 item 526A",
					"HHG", "INBOUND"));
			otherRateList.add(new Rate("LONG CARRY - IT13 item xxxxxx", "HHG",
					"INBOUND"));
			otherRateList.add(new Rate("DEBRIS REMOVAL - IT13 item", "HHG",
					"INBOUND"));

			invoiceDao.insertNewYearOther(otherRateList);
		}
	}

	public Map<String, Map<String, List<Rate>>> getSitMap(Rate rate) {
		Map<String, Map<String, List<Rate>>> sitMap = new HashMap<String, Map<String, List<Rate>>>();
		List<Rate> sitList = invoiceDao.getSitList(rate);

		Map<String, List<Rate>> sitInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> sitOutboundMap = new HashMap<String, List<Rate>>();

		List<Rate> sitOBUBList = new ArrayList<Rate>();
		List<Rate> sitOBHHGList = new ArrayList<Rate>();

		List<Rate> sitIBUBList = new ArrayList<Rate>();
		List<Rate> sitIBHHGList = new ArrayList<Rate>();

		for (Rate paramRate : sitList) {
			if ("OUTBOUND".equals(paramRate.getProcess())) {
				if ("UB".equals(paramRate.getCode())) {
					sitOBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					sitOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())) {

				if ("UB".equals(paramRate.getCode())) {
					sitIBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					sitIBHHGList.add(paramRate);
				}
			}
		}

		sitOutboundMap.put("UB", sitOBUBList);
		sitOutboundMap.put("HHG", sitOBHHGList);

		sitInboundMap.put("UB", sitIBUBList);
		sitInboundMap.put("HHG", sitIBHHGList);

		sitMap.put("OUTBOUND", sitOutboundMap);
		sitMap.put("INBOUND", sitInboundMap);

		return sitMap;
	}

	public Map<String, Map<String, List<Rate>>> getOtherMap(Rate rate) {
		Map<String, Map<String, List<Rate>>> otherMap = new HashMap<String, Map<String, List<Rate>>>();
		List<Rate> otherList = invoiceDao.getOtherList(rate);

		Map<String, List<Rate>> otherInboundMap = new HashMap<String, List<Rate>>();
		Map<String, List<Rate>> otherOutboundMap = new HashMap<String, List<Rate>>();

		List<Rate> otherOBUBList = new ArrayList<Rate>();
		List<Rate> otherOBHHGList = new ArrayList<Rate>();

		List<Rate> otherIBUBList = new ArrayList<Rate>();
		List<Rate> otherIBHHGList = new ArrayList<Rate>();

		for (Rate paramRate : otherList) {
			if ("OUTBOUND".equals(paramRate.getProcess())) {
				if ("UB".equals(paramRate.getCode())) {
					otherOBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					otherOBHHGList.add(paramRate);
				}
			} else if ("INBOUND".equals(paramRate.getProcess())) {

				if ("UB".equals(paramRate.getCode())) {
					otherIBUBList.add(paramRate);
				} else if ("HHG".equals(paramRate.getCode())) {
					otherIBHHGList.add(paramRate);
				}
			}
		}

		otherOutboundMap.put("UB", otherOBUBList);
		otherOutboundMap.put("HHG", otherOBHHGList);

		otherInboundMap.put("UB", otherIBUBList);
		otherInboundMap.put("HHG", otherIBHHGList);

		otherMap.put("OUTBOUND", otherOutboundMap);
		otherMap.put("INBOUND", otherInboundMap);

		return otherMap;
	}

	public void basicInsert(Rate rate) {
		int checkRate = invoiceDao.getBasicRateCheck(rate);

		if (checkRate == 0) {
			invoiceDao.basicInsert(rate);
		} else {
			invoiceDao.basicUpdate(rate);
		}
	}

	public Map<String, Map<String, Map<String, Rate>>> getBasicMap(Rate rate) {
		Map<String, Map<String, Map<String, Rate>>> basicMap = new HashMap<String, Map<String, Map<String, Rate>>>();

		List<Rate> rateList = invoiceDao.getBasicRateList(rate);
		String beforeTsp;
		String currentTsp;

		int rateListSize = rateList.size();

		if (rateListSize == 0) {
			beforeTsp = new String();
		} else {
			beforeTsp = rateList.get(0).getTsp();
		}

		// inbound List Map
		Map<String, Map<String, Rate>> inboundMap = new HashMap<String, Map<String, Rate>>();
		Map<String, Rate> inboundBasicMap = new HashMap<String, Rate>();
		for (int i = 0; i < rateListSize; i++) {
			Rate rateTemp = rateList.get(i);

			currentTsp = rateTemp.getTsp();

			if (!beforeTsp.equals(currentTsp)) {
				inboundMap.put(beforeTsp, inboundBasicMap);
				inboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			if ("inbound".equals(rateTemp.getProcess())) {
				inboundBasicMap.put(rateTemp.getCode(), rateTemp);
			}

			if (i != rateListSize - 1 && rateListSize > 1
					&& "outbound".equals(rateList.get(i + 1).getProcess())) {
				inboundMap.put(beforeTsp, inboundBasicMap);
				break;
			} else if (i == rateListSize - 1) {
				inboundMap.put(currentTsp, inboundBasicMap);
			}
		}

		// outbound List Map
		Map<String, Rate> outboundBasicMap = new HashMap<String, Rate>();
		Map<String, Map<String, Rate>> outboundMap = new HashMap<String, Map<String, Rate>>();
		for (int i = 0; i < rateListSize; i++) {
			Rate rateTemp = rateList.get(i);

			if ("inbound".equals(rateTemp.getProcess()))
				continue;

			currentTsp = rateTemp.getTsp();

			if (!beforeTsp.equals(currentTsp)) {
				outboundMap.put(beforeTsp, outboundBasicMap);
				outboundBasicMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			if ("outbound".equals(rateTemp.getProcess())) {
				if (rateTemp.getObType() != null) {
					outboundBasicMap
							.put(rateTemp.getCode() + rateTemp.getObType(),
									rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				} else {
					outboundBasicMap.put(rateTemp.getCode(), rateTemp);
					outboundMap.put(rateTemp.getTsp(), outboundBasicMap);
				}
			}

			if (i == rateListSize - 1) {
				outboundMap.put(currentTsp, outboundBasicMap);
			}
		}

		basicMap.put("inbound", inboundMap);
		basicMap.put("outbound", outboundMap);

		return basicMap;
	}

	public void etcInsert(Rate rate) {
		int checkRate = invoiceDao.getEtcCheck(rate);

		if (checkRate == 0) {
			invoiceDao.etcInsert(rate);
		} else {
			invoiceDao.etcUpdate(rate);
		}
	}

	public void containerInsert(Rate rate) {
		int checkRate = invoiceDao.getContainerCheck(rate);

		if (checkRate == 0) {
			invoiceDao.containerInsert(rate);
		} else {
			invoiceDao.containerUpdate(rate);
		}
	}

	public Map<String, Map<String, Rate>> getContainerMap(Rate rate) {
		Map<String, Map<String, Rate>> containerMap = new HashMap<String, Map<String, Rate>>();
		Map<String, Rate> statusMap = new HashMap<String, Rate>();

		String beforeTsp;
		String currentTsp;

		List<Rate> containerList = invoiceDao.getContainerList(rate);
		int containerListSize = containerList.size();

		if (containerListSize == 0) {
			beforeTsp = new String();
		} else {
			beforeTsp = containerList.get(0).getTsp();
		}

		for (int i = 0; i < containerListSize; i++) {
			Rate rateTemp = containerList.get(i);

			currentTsp = rateTemp.getTsp();
			if (!currentTsp.equals(beforeTsp)) {
				containerMap.put(beforeTsp, statusMap);
				statusMap = new HashMap<String, Rate>();
				beforeTsp = currentTsp;
			}

			statusMap.put(rateTemp.getContainerStatus(), rateTemp);

			if (i == containerListSize - 1) {
				containerMap.put(currentTsp, statusMap);
			}
		}

		return containerMap;
	}

	public void sitInsert(Rate rate) {
		invoiceDao.sitUpdate(rate);
	}

	public void otherInsert(Rate rate) {
		invoiceDao.otherUpdate(rate);
	}

	public int getInvoiceListCount(InvoiceFilter invoiceFilter, String process) {
		invoiceFilter.setProcess(process);

		return invoiceDao.getInvoiceListCount(invoiceFilter);
	}

	public List<Invoice> getInvoiceList(InvoiceFilter invoiceFilter) {
		return invoiceDao.getInvoiceList(invoiceFilter);
	}

	public Invoice invoiceListAdd(Invoice invoice, String process) {
		String[] invoiceGblSeqList = invoice.getSeqList().split(",");

		List<InvoiceGbl> settingGblList = new ArrayList<InvoiceGbl>();

		for (String gblSeq : invoiceGblSeqList) {
			if (process.equals("outbound")) {
				settingGblList.add(invoiceDao
						.getInvoiceSettingGblListContent(gblSeq));
			} else if (process.equals("inbound")) {
				settingGblList.add(invoiceDao
						.getInvoiceSettingGblListContentIb(gblSeq));
			}
		}

		invoice.setTsp(settingGblList.get(0).getTsp());
		invoice.setStartDate(settingGblList.get(0).getPud());
		invoice.setEndDate(settingGblList.get(settingGblList.size() - 1)
				.getPud());

		if (invoiceDao.checkTodayInvoiceNo() != null) {
			String invoiceNo = invoiceDao.checkTodayInvoiceNo();
			String invoiceNoCount = invoiceNo.substring(7, invoiceNo.length());

			invoice.setInvoiceNo(DateUtil.getToday("YYYYMMDD")
					+ Integer.toString(Integer.parseInt(invoiceNoCount) + 1));
		} else {
			invoice.setInvoiceNo(DateUtil.getToday("YYYYMMDD") + "1");
		}

		if (settingGblList.size() > 0) {
			invoice.setProcess(process);
			invoiceDao.insertInvoice(invoice);
			for (InvoiceGbl invoiceGblTemp : settingGblList) {
				invoiceGblTemp.setInvoiceListSeq(invoice.getSeq());
				invoiceDao.insertInvoiceGbl(invoiceGblTemp);

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 1);
				map.put("seq", invoiceGblTemp.getGblSeq());

				if(process.equals("outbound")){
					outboundDao.updateGblStatus(map);
				} else if (process.equals("inbound")){
					inboundDao.updateGblStatus(map);
				}
			}
		}

		Invoice returnInvoice = invoiceDao.getInvoice(invoice);

		return returnInvoice;
	}

	public List<InvoiceGbl> getInvoiceGblList(Integer seq) {
		return invoiceDao.getInvoiceGblList(seq);
	}

	public void invoiceDelete(Invoice invoice) {
		List<InvoiceGbl> invoiceGblList = invoiceDao
				.getInvoiceGblListByInvoice(invoice);

		if (invoiceGblList.size() > 0) {
			for (InvoiceGbl invoiceGblTemp : invoiceGblList) {

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 0);
				map.put("seq", invoiceGblTemp.getGblSeq());

				outboundDao.updateGblStatus(map);
			}
		}

		invoiceDao.deleteInvoice(invoice);
	}
	
	public void invoiceDelete(Invoice invoice, String process) {
		List<InvoiceGbl> invoiceGblList = invoiceDao
				.getInvoiceGblListByInvoice(invoice);

		if (invoiceGblList.size() > 0) {
			for (InvoiceGbl invoiceGblTemp : invoiceGblList) {

				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("invoice", 0);
				map.put("seq", invoiceGblTemp.getGblSeq());

				if(process.equals("outbound")){
					outboundDao.updateGblStatus(map);
				} else if(process.equals("inbound")) {
					inboundDao.updateGblStatus(map);
				}
			}
		}

		invoiceDao.deleteInvoice(invoice);
	}

	public List<InvoiceGblContent> getInvoiceGblContentList(Integer invoiceSeq,
			Integer invoiceGblSeq, Integer gblSeq, String process) {

		List<InvoiceGblContent> invoiceGblContentList = new ArrayList<InvoiceGblContent>();

		if (process.equals("outbound")) {
			Double totalAmount = 0.0;

			Integer checkInvoiceGblContentCount = invoiceDao
					.getInvoiceGblContentCount(invoiceGblSeq);

			GBL gbl = outboundDao.getGbl(gblSeq);

			// 1. PACKING CHARGE
			InvoiceGblContent packingChargeContent = new InvoiceGblContent();
			Rate rate = new Rate();
			Double totalGblWeight = 0.0;
			Double gblWeight = 0.0;
			Double typeIIWeight = 0.0;
			Double overFlowWeight = 0.0;
			Double packingCharge = 0.0;
			Double typeIICharge = 0.0;
			Double overFlowCharge = 0.0;

			String codeStr = null;
			if (gbl.getCode().equals("3") || gbl.getCode().equals("4")
					|| gbl.getCode().equals("T")) {
				codeStr = "HHG";
			} else if (gbl.getCode().equals("J") || gbl.getCode().equals("8")
					|| gbl.getCode().equals("7")) {
				codeStr = "UB";
			}

			List<Weightcertificate> weightcertificateList = outboundDao
					.getWeightcertificateList(gbl.getSeq().toString());

			for (Weightcertificate weightcertificate : weightcertificateList) {
				if ("HHG".equals(codeStr)) {
					gblWeight = Double.parseDouble(weightcertificate.getNet());
					rate.setObType(weightcertificate.getType());
					if (gblWeight < 500) {
						gblWeight = 500.0;
					}
				} else if ("UB".equals(codeStr)) {
					gblWeight = Double
							.parseDouble(weightcertificate.getGross());
					if (gblWeight < 300) {
						gblWeight = 300.0;
					}
				}

				rate.setCode(gbl.getCode());
				rate.setTsp(gbl.getScac());
				rate.setProcess(process.toUpperCase());
				rate.setWriteYear(gbl.getPud());

				Rate gblRate = invoiceDao.getBasicRate(rate);
			

				if("HHG".equals(codeStr)){
					if(rate.getObType().equals("typeII")){
						typeIIWeight += gblWeight;
						typeIICharge += gblWeight * gblRate.getRate();
					} else if (rate.getObType().equals("O/F")){
						overFlowWeight += gblWeight;
						overFlowCharge += gblWeight * gblRate.getRate();						
					}
					
					packingCharge = overFlowCharge + typeIICharge;
				} else {
					packingCharge += gblWeight * gblRate.getRate();
				}
				
				totalGblWeight += gblWeight;
			}

			totalAmount += packingCharge;

			Integer checkInvoiceContentGetSeq;
			
			if("HHG".equals(codeStr)){
				packingChargeContent.setChargingItem("PACKING CHARGE TYPEII");
				packingChargeContent.setQuantity(typeIIWeight  + "LBS");
				packingChargeContent.setAmount(typeIICharge.toString());
				packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
	
				invoiceGblContentList.add(packingChargeContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(packingChargeContent);
	
				if (checkInvoiceContentGetSeq != null) {
					packingChargeContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(packingChargeContent);
				} else {
					invoiceDao.insertInvoiceGblContent(packingChargeContent);
				}
				
				packingChargeContent = new InvoiceGblContent();
	
				packingChargeContent.setChargingItem("PACKING CHARGE O/F");
				packingChargeContent.setQuantity(overFlowWeight + "LBS");
				packingChargeContent.setAmount(overFlowCharge.toString());
				packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
	
				invoiceGblContentList.add(packingChargeContent);
	
				Integer checkInvoiceContentGetSeq1 = invoiceDao
						.checkInvoiceContent(packingChargeContent);
	
				if (checkInvoiceContentGetSeq1 != null) {
					packingChargeContent.setSeq(checkInvoiceContentGetSeq1);
					invoiceDao.updateInvoiceGblContent(packingChargeContent);
				} else {
					invoiceDao.insertInvoiceGblContent(packingChargeContent);
				}
			} else {
				packingChargeContent.setChargingItem("PACKING CHARGE");
				packingChargeContent.setQuantity(totalGblWeight + "LBS");
				packingChargeContent.setAmount(packingCharge.toString());
				packingChargeContent.setInvoiceGblSeq(invoiceGblSeq);
	
				invoiceGblContentList.add(packingChargeContent);
	
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(packingChargeContent);
	
				if (checkInvoiceContentGetSeq != null) {
					packingChargeContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(packingChargeContent);
				} else {
					invoiceDao.insertInvoiceGblContent(packingChargeContent);
				}				
			}

			// 2. CONTAINER
			InvoiceGblContent containerInvoiceGblContent = new InvoiceGblContent();

			int usedUnitTypeII = 0;
			int newUnitTypeII = 0;
			int repairedUnitTypeII = 0;
			int usedUnitOverflow = 0;
			int newUnitOverflow = 0;
			int repairedUnitOverflow = 0;
			Integer containerCharge = 0;

			for (Weightcertificate weightCertificate : weightcertificateList) {
					if(weightCertificate.getType().equals("typeII")){
						if ("NEW".equals(weightCertificate.getStatus())) {
							newUnitTypeII += 1;
						} else if ("USED".equals(weightCertificate.getStatus())) {
							usedUnitTypeII += 1;
						} else if ("REPAIRED".equals(weightCertificate.getStatus())) {
							repairedUnitTypeII += 1;
						}
					} else{
						if ("NEW".equals(weightCertificate.getStatus())) {
							newUnitOverflow += 1;
						} else if ("USED".equals(weightCertificate.getStatus())) {
							usedUnitOverflow += 1;
						} else if ("REPAIRED".equals(weightCertificate.getStatus())) {
							repairedUnitOverflow += 1;
						}						
					}
			}

			Rate containerRateParam = new Rate();
			containerRateParam.setWriteYear(gbl.getPud());
			containerRateParam.setTsp(gbl.getScac());

			containerRateParam.setContainerStatus("new");
			Rate newRate = invoiceDao.getContainerRate(containerRateParam);

			containerRateParam.setContainerStatus("used");
			Rate usedRate = invoiceDao.getContainerRate(containerRateParam);

			containerRateParam.setContainerStatus("repair");
			Rate repairedRate = invoiceDao.getContainerRate(containerRateParam);

			int newCharge = (int) (newUnitTypeII * Double.parseDouble(newRate
					.getContainerRate()));
			int usedCharge = (int) (usedUnitTypeII * Double.parseDouble(usedRate
					.getContainerRate()));
			int repairedCharge = (int) (repairedUnitTypeII * Double
					.parseDouble(repairedRate.getContainerRate()));

			containerCharge += newCharge + usedCharge + repairedCharge;

			totalAmount += containerCharge;

			containerInvoiceGblContent.setChargingItem("CONTAINER TYPE II");
			containerInvoiceGblContent.setQuantity((newUnitTypeII + usedUnitTypeII + repairedUnitTypeII) + "pcs");
			containerInvoiceGblContent.setAmount(containerCharge.toString());
			containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);

			if(containerCharge > 0)
				invoiceGblContentList.add(containerInvoiceGblContent);

			checkInvoiceContentGetSeq = invoiceDao
					.checkInvoiceContent(containerInvoiceGblContent);

			if (checkInvoiceContentGetSeq != null) {
				containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
			} else {
				invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
			}

			containerInvoiceGblContent = new InvoiceGblContent();
			
			containerCharge = 0;

			newCharge = (int) (newUnitOverflow * Double.parseDouble(newRate
					.getContainerRate()));
			usedCharge = (int) (usedUnitOverflow * Double.parseDouble(usedRate
					.getContainerRate()));
			repairedCharge = (int) (repairedUnitOverflow * Double
					.parseDouble(repairedRate.getContainerRate()));

			containerCharge += newCharge + usedCharge + repairedCharge;

			totalAmount += containerCharge;

			containerInvoiceGblContent.setChargingItem("CONTAINER O/F");
			containerInvoiceGblContent.setQuantity(newUnitOverflow + usedUnitOverflow + repairedUnitOverflow + "pcs");
			containerInvoiceGblContent.setAmount(containerCharge.toString());
			containerInvoiceGblContent.setInvoiceGblSeq(invoiceGblSeq);

			if(containerCharge > 0)
				invoiceGblContentList.add(containerInvoiceGblContent);

			checkInvoiceContentGetSeq = invoiceDao
					.checkInvoiceContent(containerInvoiceGblContent);

			if (checkInvoiceContentGetSeq != null) {
				containerInvoiceGblContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(containerInvoiceGblContent);
			} else {
				invoiceDao.insertInvoiceGblContent(containerInvoiceGblContent);
			}

			// 3. EXTRA PICKUP CHARGE
			InvoiceGblContent extraPickUpGblContent = new InvoiceGblContent();
			Double extraPickUpCharge = 0.0;

			Rate otherRateParam = new Rate();
			otherRateParam.setProcess(process);
			otherRateParam.setWriteYear(gbl.getPud());

			Rate comprate = invoiceDao.getEtc("comprate1", gbl.getPud());

			Memorandum paramMemorandum = new Memorandum();
			paramMemorandum.setGblSeq(gbl.getSeq());
			paramMemorandum.setType("04");

			Memorandum memorandum = memorandumDao
					.getMemorandum(paramMemorandum);
			if (memorandum != null) {
			} else {
				if ("HHG".equals(codeStr)) {
					otherRateParam
							.setTitle("EXTRA PICKUP CHARGE -IT13 item 1111");
					otherRateParam.setCode("HHG");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					extraPickUpCharge = comprate.getRate()
							* extraRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam
							.setTitle("EXTRA PICKUP CHARGE - IT13 item 2222");
					otherRateParam.setCode("UB");
					Rate extraRate = invoiceDao.getOther(otherRateParam);
					extraPickUpCharge = comprate.getRate()
							* extraRate.getRate();
				}

				totalAmount += extraPickUpCharge;

				extraPickUpGblContent.setChargingItem("EXTRA PICKUP CHARGE");
				extraPickUpGblContent.setQuantity("");
				extraPickUpGblContent.setAmount(extraPickUpCharge.toString());
				extraPickUpGblContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(extraPickUpGblContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(extraPickUpGblContent);

				if (checkInvoiceContentGetSeq != null) {
					extraPickUpGblContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(extraPickUpGblContent);
				} else {
					invoiceDao.insertInvoiceGblContent(extraPickUpGblContent);
				}

			}

			// 4. TERMINATION CHARGE
			InvoiceGblContent terminationContent = new InvoiceGblContent();
			Double terminationCharge = 0.0;

			paramMemorandum = new Memorandum();
			paramMemorandum.setGblSeq(gbl.getSeq());
			paramMemorandum.setType("05");

			memorandum = memorandumDao.getMemorandum(paramMemorandum);
			if (memorandum != null) {
			} else {
				if ("HHG".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 523A");
					otherRateParam.setCode("HHG");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate.getRate()
							* terminationRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 522A");
					otherRateParam.setCode("UB");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate.getRate()
							* terminationRate.getRate();
				}

				totalAmount += terminationCharge;

				terminationContent.setChargingItem("TERMINATION CHARGE");
				terminationContent.setQuantity("");
				terminationContent.setAmount(terminationCharge.toString());
				terminationContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(terminationContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(terminationContent);

				if (checkInvoiceContentGetSeq != null) {
					terminationContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(terminationContent);
				} else {
					invoiceDao.insertInvoiceGblContent(terminationContent);
				}
			}

			// 5. DIVERSION CHARGE

			// 6. SIT_FIRST DAY(ORIGIN SIT)
			InvoiceGblContent sitFirstDayContent = new InvoiceGblContent();
			Double sitFirstDayCharge = 0.0;

			Rate sitRateParam = new Rate();
			sitRateParam.setWriteYear(gbl.getPud());
			sitRateParam.setProcess(process);

			Memorandum sitFirstDayMemoParam = new Memorandum();
			sitFirstDayMemoParam.setType("06");
			sitFirstDayMemoParam.setGblSeq(gbl.getSeq());

			Memorandum checkInvoiceSitFirstDay = memorandumDao
					.getMemorandum(sitFirstDayMemoParam);

			if (checkInvoiceSitFirstDay.getSitStartDate() != null) {
				if ("HHG".equals(codeStr)) {
					sitRateParam.setTitle("SIT-FIRST DAY -IT13 item 518C");
					sitRateParam.setCode("HHG");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					sitFirstDayCharge = totalGblWeight * comprate.getRate()
							* sitFirstDayRate.getRate();
				} else if ("UB".equals(codeStr)) {
					sitRateParam.setTitle("SIT-FIRST DAY - IT13 item 519A");
					sitRateParam.setCode("UB");
					Rate sitFirstDayRate = invoiceDao.getSit(sitRateParam);
					sitFirstDayCharge = totalGblWeight * comprate.getRate()
							* sitFirstDayRate.getRate();
				}

				totalAmount += sitFirstDayCharge;

				sitFirstDayContent.setChargingItem("SIT-FIRST DAY");
				sitFirstDayContent.setQuantity("day");
				sitFirstDayContent.setAmount(sitFirstDayCharge.toString());
				sitFirstDayContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(sitFirstDayContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitFirstDayContent);

				if (checkInvoiceContentGetSeq != null) {
					sitFirstDayContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitFirstDayContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitFirstDayContent);
				}
			}

			// 7. SIT EACH ADDITIONAL DAY(ORIGIN SIT)
			InvoiceGblContent sitEachContent = new InvoiceGblContent();
			Double sitEachDayCharge = 0.0;

			Memorandum sitEachDayMemoParam = new Memorandum();
			sitEachDayMemoParam.setType("07");
			sitEachDayMemoParam.setGblSeq(gbl.getSeq());

			Memorandum checkInvoiceEachDay = memorandumDao
					.getMemorandum(sitEachDayMemoParam);

			if (checkInvoiceEachDay.getSitEndDate() != null) {
				if ("HHG".equals(codeStr)) {
					sitRateParam
							.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 518D");
					sitRateParam.setCode("HHG");
					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);

					Integer eachDayCount = DateUtil.getDaysBetween(
							checkInvoiceSitFirstDay.getSitStartDate(),
							checkInvoiceEachDay.getSitEndDate()) - 1;

					sitEachDayCharge = totalGblWeight * eachDayCount
							* comprate.getRate() * sitEachDayRate.getRate();
				} else if ("UB".equals(codeStr)) {
					sitRateParam
							.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 519C");
					sitRateParam.setCode("UB");

					Integer eachDayCount = DateUtil.getDaysBetween(
							checkInvoiceSitFirstDay.getSitStartDate(),
							checkInvoiceEachDay.getSitEndDate()) - 1;

					Rate sitEachDayRate = invoiceDao.getSit(sitRateParam);
					sitEachDayCharge = totalGblWeight * eachDayCount
							* comprate.getRate() * sitEachDayRate.getRate();

				}

				totalAmount += sitEachDayCharge;

				sitEachContent.setChargingItem("SIT-EACH ADDITIONAL DAY");
				sitEachContent.setQuantity("days");
				sitEachContent.setAmount(sitEachDayCharge.toString());
				sitEachContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(sitEachContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitEachContent);

				if (checkInvoiceContentGetSeq != null) {
					sitEachContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitEachContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitEachContent);
				}
			}
			// 8. LONG CARRY
			if ("HHG".equals(codeStr)) {
				InvoiceGblContent longCarryContent = new InvoiceGblContent();
				Double lognCarryCharge = 0.0;

				Memorandum longCarryMemoParam = new Memorandum();
				longCarryMemoParam.setType("08");
				longCarryMemoParam.setGblSeq(gbl.getSeq());

			}

			// 9. ACCESSORIAL SERVICE CHARGE

			List<Addition> additionList = outboundDao.getAddtionList(gbl
					.getSeq().toString());
			for (Addition addition : additionList) {
				InvoiceGblContent additionContent = new InvoiceGblContent();
				additionContent.setChargingItem(addition.getTitle());
				additionContent.setQuantity("");
				additionContent.setAmount(addition.getCost().toString());
				additionContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(additionContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(additionContent);

				if (checkInvoiceContentGetSeq != null) {
					additionContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(additionContent);
				} else {
					invoiceDao.insertInvoiceGblContent(additionContent);
				}

				totalAmount += addition.getCost();
			}

			// Total Amount
			InvoiceGblContent totalContent = new InvoiceGblContent();
			totalContent.setChargingItem("Total Amount");
			totalContent.setQuantity("");
			totalContent.setAmount(totalAmount.toString());

			invoiceGblContentList.add(totalContent);

			InvoiceGbl invoiceGbl = new InvoiceGbl();
			invoiceGbl.setSeq(invoiceGblSeq);
			invoiceGbl.setAmount(totalAmount.toString());
			invoiceGbl.setComplete(true);

			invoiceDao.updateInvoiceGbl(invoiceGbl);

			invoiceDao.checkAndUpdateInvoice(invoiceSeq);
		} else {			
			Double totalAmount = 0.0;
			
			GBL gbl = inboundDao.getGbl(gblSeq);

			String codeStr = null;
			if (gbl.getCode().equals("3") || gbl.getCode().equals("4")
					|| gbl.getCode().equals("5") || gbl.getCode().equals("T")) {
				codeStr = "HHG";
			} else if (gbl.getCode().equals("J") || gbl.getCode().equals("8")
					|| gbl.getCode().equals("7")) {
				codeStr = "UB";
			}
			
			List<WeightIb> weightList = inboundDao.getWeightList(gbl.getSeq());

			Rate comprate1 = invoiceDao.getEtc("comprate1", gbl.getPud());

			// 1. Destination Service Charge
			InvoiceGblContent destinationServiceChargeContent = new InvoiceGblContent();
			
			Rate rate = new Rate();
			rate.setCode(gbl.getCode());
			rate.setTsp(gbl.getTsp());
			rate.setProcess(process.toUpperCase());
			rate.setWriteYear(gbl.getPud());
			
			Rate gblRate = invoiceDao.getBasicRate(rate);

			Map<String, Double> invoiceReturnMap = getDestinationServiceCharge(gblRate.getRate(), weightList, codeStr);
			
			totalAmount += invoiceReturnMap.get("amount");
			
			destinationServiceChargeContent.setChargingItem("DESTINATION SERVICE CHARGE");
			destinationServiceChargeContent.setQuantity(invoiceReturnMap.get("quantity").toString());
			destinationServiceChargeContent.setAmount(invoiceReturnMap.get("amount").toString());
			destinationServiceChargeContent.setInvoiceGblSeq(invoiceGblSeq);
			
			invoiceGblContentList.add(destinationServiceChargeContent);
			
			Integer checkInvoiceContentGetSeq = invoiceDao
					.checkInvoiceContent(destinationServiceChargeContent);

			if (checkInvoiceContentGetSeq != null) {
				destinationServiceChargeContent.setSeq(checkInvoiceContentGetSeq);
				invoiceDao.updateInvoiceGblContent(destinationServiceChargeContent);
			} else {
				invoiceDao.insertInvoiceGblContent(destinationServiceChargeContent);
			}
			
			// 2.SIT-FIRST DAY AND WAREHOUSE HANDLING CHARGE
			InvoiceGblContent sitFirstContent = new InvoiceGblContent();		
			
			Memorandum sitFirstMemoParam = new Memorandum();
			sitFirstMemoParam.setType("06");
			sitFirstMemoParam.setGblSeq(gbl.getSeq());			
			Memorandum sitFirstMemo = memorandumDao.getMemorandumIb(sitFirstMemoParam);
			
			if(sitFirstMemo != null){
	
				invoiceReturnMap = getSitFirstDayAndWarehouseHandlingCharge(sitFirstMemo.getSitStartDate(), weightList, codeStr, comprate1.getRate());
				
				totalAmount += invoiceReturnMap.get("amount");
				
				sitFirstContent.setChargingItem("SIT-FIRST DAY AND WAREHOUSE HANDLING CHARGE");
				sitFirstContent.setAmount(invoiceReturnMap.get("amount").toString());
				sitFirstContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitFirstContent);
				
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitFirstContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitFirstContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitFirstContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitFirstContent);
				}				
			}
			
			// 3. SIT-EACH ADDTIONAL DAY
			InvoiceGblContent sitEachContent = new InvoiceGblContent();		
			
			Memorandum sitEachParam = new Memorandum();
			sitEachParam.setType("07");
			sitEachParam.setGblSeq(gbl.getSeq());			
			Memorandum sitEachMemo = memorandumDao.getMemorandumIb(sitEachParam);

			if(sitEachMemo != null){
				Integer eachDayCount = DateUtil.getDaysBetween(
						sitFirstMemo.getSitStartDate(),
						sitEachMemo.getSitEndDate()) - 1;
		
				invoiceReturnMap = getSitEachAdditionalDay(sitEachMemo.getSitEndDate(), eachDayCount, weightList, codeStr, comprate1.getRate());
				
				totalAmount += invoiceReturnMap.get("amount");
				
				sitEachContent.setChargingItem("SIT-EACH ADDITIONAL DAY");
				sitEachContent.setAmount(invoiceReturnMap.get("amount").toString());
				sitEachContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitEachContent);
				
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitEachContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitEachContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitEachContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitEachContent);
				}	
			}
			
			//4. SIT-DELIVERY CHARGE & ADM FEE
			if ("UB".equals(codeStr)) {
				InvoiceGblContent sitDeliveryContent = new InvoiceGblContent();		
				
				boolean thirtyMile = basicService.getComareMile(gbl);
				
				invoiceReturnMap = getSitDeliveryChargeAddFee(thirtyMile, weightList, comprate1.getRate(), gbl);
				
				totalAmount += invoiceReturnMap.get("amount");
				
				sitDeliveryContent.setChargingItem("SIT-DELIVERY CHARGE & ADM FEE");
				sitDeliveryContent.setAmount(invoiceReturnMap.get("amount").toString());
				sitDeliveryContent.setInvoiceGblSeq(invoiceGblSeq);
				
				invoiceGblContentList.add(sitDeliveryContent);
				
				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(sitDeliveryContent);
	
				if (checkInvoiceContentGetSeq != null) {
					sitDeliveryContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(sitDeliveryContent);
				} else {
					invoiceDao.insertInvoiceGblContent(sitDeliveryContent);
				}	
				
			}
			
			//5. REWEIGHT CHARGE
			if(weightList.get(0).getReweight() != null && !weightList.get(0).getReweight().equals("")){
				InvoiceGblContent reweightContent = new InvoiceGblContent();		
				
				invoiceReturnMap = reweightCharge(weightList, comprate1.getRate(), codeStr);
				
				if(invoiceReturnMap.get("reweight") == 1.0){
					totalAmount += invoiceReturnMap.get("amount");
					
					reweightContent.setChargingItem("REWEIGHT CHARGE");
					reweightContent.setQuantity(invoiceReturnMap.get("quantity").toString());
					reweightContent.setAmount(invoiceReturnMap.get("amount").toString());
					reweightContent.setInvoiceGblSeq(invoiceGblSeq);
					
					invoiceGblContentList.add(reweightContent);
					
					checkInvoiceContentGetSeq = invoiceDao
							.checkInvoiceContent(reweightContent);
		
					if (checkInvoiceContentGetSeq != null) {
						reweightContent.setSeq(checkInvoiceContentGetSeq);
						invoiceDao.updateInvoiceGblContent(reweightContent);
					} else {
						invoiceDao.insertInvoiceGblContent(reweightContent);
					}
				}
			}
			
			//6. Termination charge
			InvoiceGblContent terminationContent = new InvoiceGblContent();
			Double terminationCharge = 0.0;

			Memorandum terminationMemorandumParam = new Memorandum();
			terminationMemorandumParam.setGblSeq(gbl.getSeq());
			terminationMemorandumParam.setType("05");

			Memorandum terminationMemorandum = memorandumDao.getMemorandumIb(terminationMemorandumParam);
			if (terminationMemorandum == null || terminationMemorandum.getTermination().equals("0")) {
			} else {
				Rate otherRateParam = new Rate();
				if ("HHG".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 523A");
					otherRateParam.setCode("HHG");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate1.getRate()
							* terminationRate.getRate();
				} else if ("UB".equals(codeStr)) {
					otherRateParam
							.setTitle("TERMINATION CHARGE - IT13 item 522A");
					otherRateParam.setCode("UB");
					Rate terminationRate = invoiceDao.getOther(otherRateParam);
					terminationCharge = comprate1.getRate()
							* terminationRate.getRate();
				}

				totalAmount += terminationCharge;

				terminationContent.setChargingItem("TERMINATION CHARGE");
				terminationContent.setQuantity(terminationMemorandum.getTermination());
				terminationContent.setAmount(terminationCharge.toString());
				terminationContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(terminationContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(terminationContent);

				if (checkInvoiceContentGetSeq != null) {
					terminationContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(terminationContent);
				} else {
					invoiceDao.insertInvoiceGblContent(terminationContent);
				}
			}			

			//11. Accessorial Service Charge
			List<Addition> additionList = inboundDao.getAddtionList(gbl
					.getSeq().toString());
			for (Addition addition : additionList) {
				InvoiceGblContent additionContent = new InvoiceGblContent();
				additionContent.setChargingItem(addition.getTitle());
				additionContent.setQuantity("");
				additionContent.setAmount(addition.getCost().toString());
				additionContent.setInvoiceGblSeq(invoiceGblSeq);

				invoiceGblContentList.add(additionContent);

				checkInvoiceContentGetSeq = invoiceDao
						.checkInvoiceContent(additionContent);

				if (checkInvoiceContentGetSeq != null) {
					additionContent.setSeq(checkInvoiceContentGetSeq);
					invoiceDao.updateInvoiceGblContent(additionContent);
				} else {
					invoiceDao.insertInvoiceGblContent(additionContent);
				}

				totalAmount += addition.getCost();
			}
			
			// Total Amount
			InvoiceGblContent totalContent = new InvoiceGblContent();
			totalContent.setChargingItem("Total Amount");
			totalContent.setQuantity("");
			totalContent.setAmount(totalAmount.toString());

			invoiceGblContentList.add(totalContent);

			InvoiceGbl invoiceGbl = new InvoiceGbl();
			invoiceGbl.setSeq(invoiceGblSeq);
			invoiceGbl.setAmount(totalAmount.toString());
			invoiceGbl.setComplete(true);

			invoiceDao.updateInvoiceGbl(invoiceGbl);

			invoiceDao.checkAndUpdateInvoice(invoiceSeq);
		}

		return invoiceGblContentList;
	}

	private int getUBHHGType(String value_UB_HHG) {

		// UB인지 HHG인지 종류를 구분하는 정적 메소드.
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성

		if (value_UB_HHG.indexOf("UB") < 0)
			return 0;
		else if (value_UB_HHG.indexOf("HHG") < 0)
			return 1;
		else
			return -1;

	}

	private double getGBLWeight(WeightIb weight, int type) {

		// GBL weight를 사용할 때 필요한 1-5, 1-6번 과정을 수행하는 메소드
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성

		// ///////////////////////////////////////////////////////////////////

		double gbl_weight = 0.0;

		// 1-5번 과정
		if (type == 0) // UB
			gbl_weight = Double.parseDouble(weight.getGross());
		else if (type == 1) // HHG
			gbl_weight = Double.parseDouble(weight.getNet());

		return gbl_weight;
	}	
	
	private double getGBLWeight(double weight, int type, boolean checkLast) {

		// GBL weight를 사용할 때 필요한 1-5, 1-6번 과정을 수행하는 메소드
		// 1.0 -- (2014-02-21) 강정규 : 첫 작성

		// ///////////////////////////////////////////////////////////////////

		double gbl_weight = weight;
		double minimum_weight[] = { 300.0, // UB
				500.0 }; // HHG		

		// 1-6번 과정
		if (gbl_weight < minimum_weight[type])
			gbl_weight = minimum_weight[type];

		return gbl_weight;
	}

	private Map<String, Double> getDestinationServiceCharge(double rate, List<WeightIb> weightList,
			String value_UB_HHG) {

		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경

		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();
		
		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double gbl_weight = 0.0;
		double gbl_rate = 0.0;

		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번.
		gbl_rate = rate;

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGBLWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		if(weightList.get(0).getReweight() != null && !weightList.get(0).getReweight().equals("")){
			String [] reweightList = weightList.get(0).getReweight().split("/");
			if(Double.parseDouble(reweightList[0]) < gbl_weight){
				gbl_weight = Double.parseDouble(reweightList[0]);
			}
		}
		
		returnMap.put("quantity", gbl_weight);
		returnMap.put("amount", gbl_weight * gbl_rate);

		// 최종 결과 값 반환
		return returnMap;
	}
	
	private Map<String, Double> getSitFirstDayAndWarehouseHandlingCharge(
			String sit_no, List<WeightIb> weightList, String value_UB_HHG,
			double comprate1) {

		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경

		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();

		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double gbl_weight = 0.0;
		double sit_first_day = 0.0;

		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번 (sit_no 유무 확인)
		if (sit_no.isEmpty() == true)
			return returnMap;

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGBLWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		if (ub_hhg_type == 0) {			// 4번 (UB)
			Rate rate = new Rate();
			rate.setTitle("SIT-FIRST DAY -IT13 item 518C");
			rate.setCode(value_UB_HHG);
			sit_first_day = invoiceDao.getSit(rate).getRate();
		} else if (ub_hhg_type == 1) {	// 3번 (HHG)
			Rate rate = new Rate();
			rate.setTitle("SIT-FIRST DAY - IT13 item 519A");
			rate.setCode(value_UB_HHG);
			sit_first_day = invoiceDao.getSit(rate).getRate();
		}

		returnMap.put("amount", gbl_weight * sit_first_day * comprate1);
		
		// 최종 결과 값 반환
		return returnMap;
	}
	
	private Map<String, Double> getSitEachAdditionalDay(String sit_no,
			int sit_day, List<WeightIb> weightList, String value_UB_HHG,
			double comprate1) {

		// 1.0 -- (2014-02-21) 강정규 : 첫 작성
		// 1.1 -- (2014-02-23) 박광열 : Map으로 return type change, 단일 weight 계산을 weight List로 변경

		// ///////////////////////////////////////////////////////////////////

		Map<String, Double> returnMap = new HashMap<String, Double>();

		// 계산에 필요한 변수들
		double weight_temp = 0.0;
		double gbl_weight = 0.0;
		double addition_day = 0.0;

		// UB냐 HHG냐 구분하는 변수. (0: UB, 1: HHG)
		int ub_hhg_type = getUBHHGType(value_UB_HHG);

		// ///////////////////////////////////////////////////////////////////

		// 1 & 2번 (sit_no, additionSitDay 유무 확인)
		if (sit_no.isEmpty() == true && sit_day > 0)
			return returnMap;

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGBLWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);

		if (ub_hhg_type == 0) {			// 4번 (UB)
			Rate rate = new Rate();
			rate.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 518D");
			rate.setCode(value_UB_HHG);
			addition_day = invoiceDao.getSit(rate).getRate();
		} else if (ub_hhg_type == 1) {	// 3번 (HHG)
			Rate rate = new Rate();
			rate.setTitle("SIT-EACH ADDITIONALDAY - IT13 item 519C");
			rate.setCode(value_UB_HHG);
			addition_day = invoiceDao.getSit(rate).getRate();
		}

		returnMap.put("amount", gbl_weight * addition_day * sit_day * comprate1);
		// 최종 결과 값 반환
		
		return returnMap;
	}
	
	private Map<String, Double> getSitDeliveryChargeAddFee(boolean thirtyMile, List<WeightIb> weightList, double comprate1, GBL gbl) {

		Map<String, Double> returnMap = new HashMap<String, Double>();

		double weight_temp = 0.0;
		double gbl_weight = 0.0;
		double delivery = 0.0;
		
		int ub_hhg_type = getUBHHGType("UB");

		// 5 & 6번. 사용할 Weight 종류 확인
		for( WeightIb weight : weightList ){
			weight_temp += getGBLWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		//thirtyMile = true 이상 . false 이하
		if(thirtyMile){
			
		} else {
			Rate otherParam = new Rate();
			otherParam
					.setTitle("30mile 이하 - IT13 item 521I");
			otherParam.setCode("UB");
			Rate otherRate = invoiceDao.getOther(otherParam);
			
			double origin = otherRate.getRate() * gbl_weight;
			

			otherParam
					.setTitle("30mile 이하  - IT13 item 521K (minimum per shipment)");
			otherRate = invoiceDao.getOther(otherParam);
			double minimum = otherRate.getRate();
			
			if(origin > minimum){
				delivery = origin * comprate1;
			} else {
				delivery = minimum * comprate1;
			}			
		}
		
		returnMap.put("amount", delivery);
		
		return returnMap;
	}
	
	private Map<String, Double> reweightCharge(List<WeightIb> weightList, double comprate1, String value_UB_HHG) {

		Map<String, Double> returnMap = new HashMap<String, Double>();

		double weight_temp = 0.0;
		double gbl_weight = 0.0;
		double gbl_reweight = 0.0;
		double reweight_charge = 0.0;

		int ub_hhg_type = getUBHHGType(value_UB_HHG);
		
		for( WeightIb weight : weightList ){
			weight_temp += getGBLWeight(weight, ub_hhg_type);
		}
		
		gbl_weight = getGBLWeight(weight_temp, ub_hhg_type, true);
		
		String [] reweightList = weightList.get(0).getReweight().split("/");
		gbl_reweight = Double.parseDouble(reweightList[0]);

		double subtraction = gbl_weight - gbl_reweight;
		
		if(ub_hhg_type == 1){
			Rate otherParam = new Rate();
			otherParam
					.setTitle("REWEIGHT CHARGE - IT13 item 505A");
			otherParam.setCode("HHG");
			Rate otherRate = invoiceDao.getOther(otherParam);
						
			if(gbl_weight <= 5000.0){
				
				if(subtraction <= 100 && subtraction > 0){
					reweight_charge = otherRate.getRate() * subtraction;
					
					returnMap.put("reweight", 1.0);
				} else {					
					returnMap.put("reweight", 0.0);
				}
			} else {
				if(subtraction <= (gbl_weight * 0.02) && subtraction > 0){
					reweight_charge = otherRate.getRate() * subtraction;
					
					returnMap.put("reweight", 1.0);
				} else {					
					returnMap.put("reweight", 0.0);					
				}
			}
		} else if (ub_hhg_type == 0){
			Rate otherParam = new Rate();
			otherParam
					.setTitle("REWEIGHT CHARGE - IT13 item 505B");
			otherParam.setCode("UB");
			Rate otherRate = invoiceDao.getOther(otherParam);
			if(subtraction <= 25.0 && subtraction > 0){
				reweight_charge = otherRate.getRate() * subtraction;
				
				returnMap.put("reweight", 1.0);
			} else {				
				returnMap.put("reweight", 0.0);
			}
		}	
				
		returnMap.put("quantity", subtraction);
		returnMap.put("amount", reweight_charge);
		return returnMap;	
	}
	
	/* Rate */

	public Map<String, Rate> getEtcMap(Rate rate) {
		Map<String, Rate> etcMap = new HashMap<String, Rate>();

		List<Rate> etcList = invoiceDao.getEtcList(rate);
		for (Rate rateTemp : etcList) {
			etcMap.put(rateTemp.getTitle(), rateTemp);
		}

		return etcMap;
	}

	public InvoiceGbl getInvoiceGblContentInfo(Integer invoiceGblSeq, String process) {
		if( "outbound".equals(process))
			return invoiceDao.getInvoiceGblcontentInfo(invoiceGblSeq);
		else if( "inbound".equals(process)){
			return invoiceDao.getInvoiceGblcontentInfoIb(invoiceGblSeq);
		}
		
		return new InvoiceGbl();
	}

	public int getInvoiceSettingGblListCount(InvoiceGblFilter invoiceGblFilter) {
		if (invoiceGblFilter.getProcess().equals("outbound")) {
			return invoiceDao.getInvoiceSettingGblListCount(invoiceGblFilter);
		} else if (invoiceGblFilter.getProcess().equals("inbound")) {
			return invoiceDao.getInvoiceSettingGblListIbCount(invoiceGblFilter);
		}

		return 0;
	}

	public List<GBL> getInvoiceSettingGblList(InvoiceGblFilter invoiceGblFilter) {
		if (invoiceGblFilter.getProcess().equals("outbound")) {
			return invoiceDao.getInvoiceSettingGblList(invoiceGblFilter);
		} else if (invoiceGblFilter.getProcess().equals("inbound")) {
			return invoiceDao.getInvoiceSettingGblListIb(invoiceGblFilter);
		}

		return new ArrayList<GBL>();
	}

	public Map<String, List<Code>> getInvoiceFilterMap(
			InvoiceFilter invoiceFilter) {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> carrierList = invoiceDao.getCarrierList(invoiceFilter);
		filterMap.put("tspList", carrierList);

		return filterMap;
	}

	public Map<String, List<Code>> getFilterMap() {
		Map<String, List<Code>> filterMap = new HashMap<String, List<Code>>();
		List<Code> branchList = codeDao.getAllAreaList();
		filterMap.put("branchList", branchList);

		List<Code> carrierList = outboundDao.getCarrierList();
		filterMap.put("carrierList", carrierList);

		List<Code> codeList = outboundDao.getCodeList();
		filterMap.put("codeList", codeList);

		return filterMap;
	}

	public int getInvoiceCollectionListCount(InvoiceFilter invoiceFilter,
			String process) {
		invoiceFilter.setProcess(process);

		return invoiceDao.getInvoiceCollectionListCount(invoiceFilter);
	}

	public List<Invoice> getInvoiceCollectionList(InvoiceFilter invoiceFilter) {
		return invoiceDao.getInvoiceCollectionList(invoiceFilter);
	}

	public Map<Integer, InvoiceCollection> getInvoiceCollectionMap(
			InvoiceFilter invoiceFilter) {
		Map<Integer, InvoiceCollection> map = new HashMap<Integer, InvoiceCollection>();
		List<InvoiceCollection> list = invoiceDao
				.getInvoiceCollectionListAndFlow(invoiceFilter);

		for (InvoiceCollection invoiceCollection : list) {
			map.put(invoiceCollection.getInvoiceSeq(), invoiceCollection);
		}

		return map;

	}

	public void inputCollection(Map<String, String> invoiceCollectionMap) {
		InvoiceCollection invoiceCollection = new InvoiceCollection();
		invoiceCollection.setNet(invoiceCollectionMap.get("net"));
		invoiceCollection.setDifference(invoiceCollectionMap.get("difference"));
		invoiceCollection.setState(invoiceCollectionMap.get("state"));
		invoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceCollectionMap
				.get("invoiceSeq")));

		InvoiceCollection collectionParam = invoiceDao
				.checkAndGetCollectionSeq(invoiceCollectionMap
						.get("invoiceSeq"));
		if (collectionParam != null
				&& !invoiceCollectionMap.get("flowState").equals("CLAIM")) {
			Integer net = Integer.parseInt(collectionParam.getNet())
					+ Integer.parseInt(invoiceCollection.getNet());
			Integer difference = net
					- Integer.parseInt(invoiceCollectionMap.get("amount"));
			invoiceCollection.setSeq(collectionParam.getSeq());
			if (difference == 0
					&& invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			} else if (invoiceCollectionMap.get("flowState").equals("ACCEPT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(collectionParam.getNet());
				invoiceCollection.setDifference(difference.toString());
			} else {
				invoiceCollection.setState("RESENT");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			}

			invoiceDao.updateCollectionNet(invoiceCollection);
		} else if (invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
			invoiceDao.inputCollectionNet(invoiceCollection);

		} else if (invoiceCollectionMap.get("flowState").equals("CLAIM")) {
		}

		InvoiceCollectionFlow invoiceCollectionFlow = new InvoiceCollectionFlow();
		invoiceCollectionFlow.setAmount(invoiceCollectionMap.get("flowAmount"));
		invoiceCollectionFlow.setState(invoiceCollectionMap.get("flowState"));
		invoiceCollectionFlow.setRemark(invoiceCollectionMap.get("flowRemark"));
		if (invoiceCollection.getSeq() != null) {
			invoiceCollectionFlow.setInvoiceCollectionSeq(invoiceCollection
					.getSeq());
		} else {
			invoiceCollectionFlow.setInvoiceCollectionSeq(collectionParam
					.getSeq());
		}

		invoiceDao.inputCollectionFlow(invoiceCollectionFlow);
	}

	public void invoiceCollectionDelete(Map<String, String> invoiceCollection) {
		String invoiceSeq = invoiceCollection.get("invoiceSeq");
		String flowSeq = invoiceCollection.get("flowSeq");
		String collectionSeq = invoiceCollection.get("collectionSeq");
		String state = invoiceCollection.get("state");
		String amount = invoiceCollection.get("amount");

		Integer count = Integer.parseInt(invoiceCollection.get("count"));

		if (state.equals("DEPOSIT")) {
			if (count == 1) {
				invoiceDao.deleteInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteInvoiceCollectionFlow(flowSeq);

				InvoiceCollection collection = invoiceDao
						.checkAndGetCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
				Integer net = Integer.parseInt(collection.getNet())
						- Integer.parseInt(amount);
				collectionParam.setNet(net.toString());

				Integer difference = Integer.parseInt(collection
						.getDifference()) - Integer.parseInt(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");

				collectionParam.setSeq(Integer.parseInt(collectionSeq));

				invoiceDao.updateCollectionNet(collectionParam);
			}
		} else if (state.equals("ACCEPT")) {
			invoiceDao.deleteInvoiceCollectionFlow(flowSeq);
			InvoiceCollection collection = invoiceDao
					.checkAndGetCollectionSeq(invoiceSeq);
			InvoiceCollection collectionParam = new InvoiceCollection();

			Integer difference = Integer.parseInt(collection.getDifference())
					- Integer.parseInt(amount);
			collectionParam.setDifference(difference.toString());
			collectionParam.setState("RESENT");

			collectionParam.setNet(collection.getNet());

			collectionParam.setSeq(Integer.parseInt(collectionSeq));

			invoiceDao.updateCollectionNet(collectionParam);

		} else if (state.equals("CLAIM")) {
			invoiceDao.deleteInvoiceCollectionFlow(flowSeq);
		}
	}

	public Map<Integer, InvoiceCollection> getInvoiceCollectionGblMap(
			Integer seq) {
		Map<Integer, InvoiceCollection> map = new HashMap<Integer, InvoiceCollection>();
		List<InvoiceCollection> list = invoiceDao
				.getInvoiceCollectionGblListAndFlow(seq);

		for (InvoiceCollection invoiceCollection : list) {
			map.put(invoiceCollection.getInvoiceSeq(), invoiceCollection);
		}

		return map;

	}

	public void inputGblCollection(Map<String, String> invoiceCollectionMap) {
		InvoiceCollection invoiceCollection = new InvoiceCollection();
		invoiceCollection.setNet(invoiceCollectionMap.get("net"));
		invoiceCollection.setDifference(invoiceCollectionMap.get("difference"));
		invoiceCollection.setState(invoiceCollectionMap.get("state"));
		invoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceCollectionMap
				.get("invoiceSeq")));

		InvoiceCollection collectionParam = invoiceDao
				.checkAndGetGblCollectionSeq(invoiceCollectionMap
						.get("invoiceSeq"));
		
		if (collectionParam != null
				&& !invoiceCollectionMap.get("flowState").equals("CLAIM")) {
			Integer net = Integer.parseInt(collectionParam.getNet())
					+ Integer.parseInt(invoiceCollection.getNet());
			Integer difference = (int) (net - Double
					.parseDouble(invoiceCollectionMap.get("amount")));
			invoiceCollection.setSeq(collectionParam.getSeq());
			if (difference == 0
					&& invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			} else if (invoiceCollectionMap.get("flowState").equals("ACCEPT")) {
				invoiceCollection.setState("COMPLETE");
				invoiceCollection.setNet(collectionParam.getNet());
				invoiceCollection.setDifference(difference.toString());
			} else {
				invoiceCollection.setState("RESENT");
				invoiceCollection.setNet(net.toString());
				invoiceCollection.setDifference(difference.toString());
			}

			invoiceDao.updateGblCollectionNet(invoiceCollection);
		} else { //if (invoiceCollectionMap.get("flowState").equals("DEPOSIT")) {
			invoiceDao.inputGblCollectionNet(invoiceCollection);

		} /*else if (invoiceCollectionMap.get("flowState").equals("CLAIM")) {
		}*/

		InvoiceCollectionFlow invoiceCollectionFlow = new InvoiceCollectionFlow();
		invoiceCollectionFlow.setAmount(invoiceCollectionMap.get("flowAmount"));
		invoiceCollectionFlow.setState(invoiceCollectionMap.get("flowState"));
		invoiceCollectionFlow.setRemark(invoiceCollectionMap.get("flowRemark"));
		if (invoiceCollection.getSeq() != null) {
			invoiceCollectionFlow.setInvoiceCollectionSeq(invoiceCollection
					.getSeq());
		} else {
			invoiceCollectionFlow.setInvoiceCollectionSeq(Integer.parseInt(invoiceCollectionMap
					.get("gblSeq")));
		}

		invoiceDao.inputGblCollectionFlow(invoiceCollectionFlow);

		// 내부 GBL 입력 완료후 합이나, 상태를 invoice로 업데이트
		InvoiceCollection parentInvoiceCollection = new InvoiceCollection();
		String invoiceSeq = invoiceCollectionMap.get("invoiceNormalSeq");
		List<InvoiceCollection> invoiceCollectionGblList = invoiceDao
				.getInvoiceCollectionGblListAndFlow(Integer
						.parseInt(invoiceSeq));

		Double invoiceCollectionNetSum = 0.0;
		Double invoiceCollectionDifferencSum = 0.0;
		boolean checkResent = false;

		for (InvoiceCollection invoiceCollectionGbl : invoiceCollectionGblList) {
			invoiceCollectionNetSum += Double.parseDouble(invoiceCollectionGbl
					.getNet());
			invoiceCollectionDifferencSum += Double
					.parseDouble(invoiceCollectionGbl.getDifference());
			if (invoiceCollectionGbl.getState().equals("RESENT")
					|| invoiceCollectionGbl.getState() == null
					|| invoiceCollectionGbl.getState().equals("")) {
				checkResent = true;
			}
		}

		parentInvoiceCollection.setNet(invoiceCollectionNetSum.toString());
		parentInvoiceCollection.setDifference(invoiceCollectionDifferencSum
				.toString());

		if (checkResent) {
			parentInvoiceCollection.setState("RESENT");
		} else {
			parentInvoiceCollection.setState("COMPLETE");
		}

		parentInvoiceCollection.setInvoiceSeq(Integer.parseInt(invoiceSeq));

		Integer invoiceCollectionSeq = invoiceDao
				.getInvoiceCollectionSeq(Integer.parseInt(invoiceSeq));
		if (invoiceCollectionSeq != null && invoiceCollectionSeq > 0) {
			parentInvoiceCollection.setSeq(invoiceCollectionSeq);
			invoiceDao.updateCollectionNet(parentInvoiceCollection);
		} else {
			invoiceDao.inputCollectionNet(parentInvoiceCollection);
		}
	}

	public void invoiceGblCollectionDelete(Map<String, String> invoiceCollection) {
		String invoiceSeq = invoiceCollection.get("invoiceSeq");
		String flowSeq = invoiceCollection.get("flowSeq");
		String collectionSeq = invoiceCollection.get("collectionSeq");
		String state = invoiceCollection.get("state");
		String amount = invoiceCollection.get("amount");

		Integer count = Integer.parseInt(invoiceCollection.get("count"));

		if (state.equals("DEPOSIT")) {
			if (count == 1) {
				invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);

				InvoiceCollection collection = invoiceDao
						.checkAndGetGblCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
				Integer net = Integer.parseInt(collection.getNet())
						- Integer.parseInt(amount);
				collectionParam.setNet(net.toString());

				Integer difference = Integer.parseInt(collection
						.getDifference()) - Integer.parseInt(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");

				collectionParam.setSeq(Integer.parseInt(collectionSeq));

				invoiceDao.updateGblCollectionNet(collectionParam);
			}
		} else if (state.equals("ACCEPT")) {
			
			if (count == 1) {
				invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);
				InvoiceCollection collection = invoiceDao
						.checkAndGetGblCollectionSeq(invoiceSeq);
				InvoiceCollection collectionParam = new InvoiceCollection();
	
				Integer difference = Integer.parseInt(collection.getDifference())
						- Integer.parseInt(amount);
				collectionParam.setDifference(difference.toString());
				collectionParam.setState("RESENT");
	
				collectionParam.setNet(collection.getNet());
	
				collectionParam.setSeq(Integer.parseInt(collectionSeq));
	
				invoiceDao.updateGblCollectionNet(collectionParam);
			}

		} else if (state.equals("CLAIM")) {
			if (count == 1) {
				invoiceDao.deleteGblInvoiceCollection(collectionSeq);
			} else {
				invoiceDao.deleteGblInvoiceCollectionFlow(flowSeq);
			}
		}

		InvoiceCollection parentInvoiceCollection = new InvoiceCollection();
		String invoiceNormalSeq = invoiceCollection.get("invoiceNormalSeq");
		List<InvoiceCollection> invoiceCollectionGblList = invoiceDao
				.getInvoiceCollectionGblListAndFlow(Integer
						.parseInt(invoiceNormalSeq));

		Double invoiceCollectionNetSum = 0.0;
		Double invoiceCollectionDifferencSum = 0.0;
		int checkResent = 0; // 0 : complete , 1 : resent , 2 : ''

		for (InvoiceCollection invoiceCollectionGbl : invoiceCollectionGblList) {
			invoiceCollectionNetSum += Double.parseDouble(invoiceCollectionGbl
					.getNet());
			invoiceCollectionDifferencSum += Double
					.parseDouble(invoiceCollectionGbl.getDifference());
			if (invoiceCollectionGbl.getState().equals("RESENT")) {
				checkResent = 1;
			} else if (invoiceCollectionGbl.getState() == null
					|| invoiceCollectionGbl.getState().equals("")) {
				checkResent = 2;
			} else {
				checkResent = 0;
			}
		}

		if (invoiceCollectionGblList.size() == 0) {
			checkResent = 2;
		}

		parentInvoiceCollection.setNet(invoiceCollectionNetSum.toString());
		parentInvoiceCollection.setDifference(invoiceCollectionDifferencSum
				.toString());

		if (checkResent == 1) {
			parentInvoiceCollection.setState("RESENT");
		} else if (checkResent == 2) {
			parentInvoiceCollection.setState("");
			parentInvoiceCollection.setNet("");
		} else {
			parentInvoiceCollection.setState("COMPLETE");
		}

		parentInvoiceCollection.setInvoiceSeq(Integer
				.parseInt(invoiceNormalSeq));

		Integer invoiceCollectionSeq = invoiceDao
				.getInvoiceCollectionSeq(Integer.parseInt(invoiceNormalSeq));

		parentInvoiceCollection.setSeq(invoiceCollectionSeq);
		invoiceDao.updateCollectionNet(parentInvoiceCollection);
	}

	public void invoiceCollectionRemarkInput(
			Map<String, String> invoiceCollection) {
		invoiceDao.invoiceCollectionRemarkInput(invoiceCollection);
	}

	public Invoice getInvoiceByInvoiceSeq(Integer seq) {
		Invoice invoice = new Invoice();
		invoice.setSeq(seq);
		return invoiceDao.getInvoice(invoice);
	}

	public List<String> getYearList() {
		return invoiceDao.getYearList();
	}

	public Map<Integer, List<InvoiceGblContent>> getInvoicePrintMap(
			List<InvoiceGbl> invoiceGblList, String process) {
		
		Map<Integer, List<InvoiceGblContent>> returnMap = new HashMap<Integer, List<InvoiceGblContent>>();
		
		for(InvoiceGbl invoiceGbl : invoiceGblList){
			returnMap.put(invoiceGbl.getSeq(), getInvoiceGblContentList(invoiceGbl.getInvoiceListSeq(), invoiceGbl.getSeq(), invoiceGbl.getGblSeq(), process));
		}
		
		return returnMap;
	}

	public List<Integer> getInvoiceSeqListByGblSeq(Integer seq) {
		return invoiceDao.getInvoiceSeqListByGblSeq(seq);
	}

	public void deleteInvoice(Integer invoiceSeq) {
		Invoice invoice = new Invoice();
		
		invoice.setSeq(invoiceSeq);
		
		invoiceDao.deleteInvoice(invoice);
	}
}
