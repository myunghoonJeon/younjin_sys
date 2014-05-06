package org.youngjin.net;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.youngjin.net.inbound.WeightIb;

public class GBL {

	// outbound
	private Integer seq;
	private String no;
	private String customerName;
	private String pud;
	private String rank;
	private String code;
	private String scac;
	private String originAddress;
	private String ssn;
	private String areaLocal;
	private String phoneNo;
	private String email;
	private String rdd;
	private String vessel;
	private String pcs;
	private String lbs;
	private String cuft;
	private String usNo;
	private String pod;
	private String poe;
	private String consoleCompany;
	private String containerNo;
	private String hbBookingNo;
	private String clpNo;
	private String sealNo;
	private String originPort;
	private String destPort;
	private String destState;
	private String export;
	private String originGBlock;
	private String destGBlock;
	private String truckmanifastDate;
	private String originCity;
	private String tac;
	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	private String milSVC;

	private Integer gblFileNo;

	private Boolean truckCheck;
	private Boolean bookingCheck;
	private Boolean houseCheck;

	private Integer truckSeq;
	private Integer bookingSeq;
	private Integer houseSeq;

	private String etd;
	private String eta;
	private String blNo;

	private Boolean seperateFlag;
	private Boolean houseSeperateFlag;

	private String remarkRdd;
	private String remarkTac;
	private String consignee;

	private String houseVessel;
	private String houseVoyage;
	private String houseCompany;
	private String houseConsignee;

	// inbound
	private String gblNo;
	private String shipperName;
	private String tsp;
	private String destAddress;
	private String arriveDate;
	private String awbNo;
	private String pmjDate;
	private String fright;
	private String eMailAddress;
	private String oblNo;
	private String vessle;
	private String blCompany;
	private String remark;
	private String sitIn;
	private String sitOut;
	private String sitNo;
	private String rate;
	private String yjNo;
	private String totalPcs;
	private String phone;
	private String address;
	private String onHandDate;
	private String gbloc;
	private String destinationGbloc;
	private String itemsPieces;
	private String grossWeight;
	private String netWeight;
	private String storedAt;
	private String onHandContentSeq;
	private String truckDate;
	
	public String getTruckmanifastDate() {
		return truckmanifastDate;
	}

	public void setTruckmanifastDate(String truckmanifastDate) {
		this.truckmanifastDate = truckmanifastDate;
	}

	public String getTruckDate() {
		return truckDate;
	}

	public void setTruckDate(String k) {
		this.truckDate = k;
	}

	// reweight
	private String deliDate;
	private String gblRate31;
	private String reweightGross;

	private WeightIb weightIb;

	public Boolean getTruckCheck() {
		return truckCheck;
	}

	public void setTruckCheck(Boolean truckCheck) {
		this.truckCheck = truckCheck;
	}

	public String getPcs() {
		return pcs;
	}

	public void setPcs(String pcs) {
		this.pcs = pcs;
	}

	public String getLbs() {
		return lbs;
	}

	public void setLbs(String lbs) {
		this.lbs = lbs;
	}

	public String getCuft() {
		return cuft;
	}

	public void setCuft(String cuft) {
		this.cuft = cuft;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPud() {
		return pud;
	}

	public void setPud(String pud) {
		this.pud = pud;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAreaLocal() {
		return areaLocal;
	}

	public void setAreaLocal(String areaLocal) {
		this.areaLocal = areaLocal;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsNo() {
		return usNo;
	}

	public void setUsNo(String usNo) {
		this.usNo = usNo;
	}

	public String getRdd() {
		return rdd;
	}

	public void setRdd(String rdd) {
		this.rdd = rdd;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	public String getPoe() {
		return poe;
	}

	public void setPoe(String poe) {
		this.poe = poe;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getConsoleCompany() {
		return consoleCompany;
	}

	public void setConsoleCompany(String consoleCompany) {
		this.consoleCompany = consoleCompany;
	}

	public String getHbBookingNo() {
		return hbBookingNo;
	}

	public void setHbBookingNo(String hbBookingNo) {
		this.hbBookingNo = hbBookingNo;
	}

	public String getClpNo() {
		return clpNo;
	}

	public void setClpNo(String clpNo) {
		this.clpNo = clpNo;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getOriginPort() {
		return originPort;
	}

	public void setOriginPort(String originPort) {
		this.originPort = originPort;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getDestState() {
		return destState;
	}

	public void setDestState(String destState) {
		this.destState = destState;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOriginGBlock() {
		return originGBlock;
	}

	public void setOriginGBlock(String originGBlock) {
		this.originGBlock = originGBlock;
	}

	public String getDestGBlock() {
		return destGBlock;
	}

	public void setDestGBlock(String destGBlock) {
		this.destGBlock = destGBlock;
	}

	public String getMilSVC() {
		return milSVC;
	}

	public void setMilSVC(String milSVC) {
		this.milSVC = milSVC;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public Integer getGblFileNo() {
		return gblFileNo;
	}

	public void setGblFileNo(Integer gblFileNo) {
		this.gblFileNo = gblFileNo;
	}

	public String getEtd() {
		return etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public Boolean getSeperateFlag() {
		return seperateFlag;
	}

	public void setSeperateFlag(Boolean seperateFlag) {
		this.seperateFlag = seperateFlag;
	}

	public Boolean getHouseSeperateFlag() {
		return houseSeperateFlag;
	}

	public void setHouseSeperateFlag(Boolean houseSeperateFlag) {
		this.houseSeperateFlag = houseSeperateFlag;
	}

	public String getRemarkRdd() {
		return remarkRdd;
	}

	public void setRemarkRdd(String remarkRdd) {
		this.remarkRdd = remarkRdd;
	}

	public String getRemarkTac() {
		return remarkTac;
	}

	public void setRemarkTac(String remarkTac) {
		this.remarkTac = remarkTac;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getHouseVessel() {
		return houseVessel;
	}

	public void setHouseVessel(String houseVessel) {
		this.houseVessel = houseVessel;
	}

	public String getHouseVoyage() {
		return houseVoyage;
	}

	public void setHouseVoyage(String houseVoyage) {
		this.houseVoyage = houseVoyage;
	}

	public String getHouseCompany() {
		return houseCompany;
	}

	public void setHouseCompany(String houseCompany) {
		this.houseCompany = houseCompany;
	}

	public String getHouseConsignee() {
		return houseConsignee;
	}

	public void setHouseConsignee(String houseConsignee) {
		this.houseConsignee = houseConsignee;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public String getExport() {
		return export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	// inbound

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getAwbNo() {
		return awbNo;
	}

	public void setAwbNo(String awbNo) {
		this.awbNo = awbNo;
	}

	public String getPmjDate() {
		return pmjDate;
	}

	public void setPmjDate(String pmjDate) {
		this.pmjDate = pmjDate;
	}

	public String getFright() {
		return fright;
	}

	public void setFright(String fright) {
		this.fright = fright;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public String getOblNo() {
		return oblNo;
	}

	public void setOblNo(String oblNo) {
		this.oblNo = oblNo;
	}

	public String getVessle() {
		return vessle;
	}

	public void setVessle(String vessle) {
		this.vessle = vessle;
	}

	public String getBlCompany() {
		return blCompany;
	}

	public void setBlCompany(String blCompany) {
		this.blCompany = blCompany;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getYjNo() {
		return yjNo;
	}

	public void setYjNo(String yjNo) {
		this.yjNo = yjNo;
	}

	public String getTotalPcs() {
		return totalPcs;
	}

	public void setTotalPcs(String totalPcs) {
		this.totalPcs = totalPcs;
	}

	public WeightIb getWeightIb() {
		return weightIb;
	}

	public void setWeightIb(WeightIb weightIb) {
		this.weightIb = weightIb;
	}

	public String getDeliDate() {
		return deliDate;
	}

	public void setDeliDate(String deliDate) {
		this.deliDate = deliDate;
	}

	public String getGblRate31() {
		return gblRate31;
	}

	public void setGblRate31(String gblRate31) {
		this.gblRate31 = gblRate31;
	}

	public String getReweightGross() {
		return reweightGross;
	}

	public void setReweightGross(String reweightGross) {
		this.reweightGross = reweightGross;
	}

	public String getSitIn() {
		return sitIn;
	}

	public void setSitIn(String sitIn) {
		this.sitIn = sitIn;
	}

	public String getSitOut() {
		return sitOut;
	}

	public void setSitOut(String sitOut) {
		this.sitOut = sitOut;
	}

	public String getSitNo() {
		return sitNo;
	}

	public void setSitNo(String sitNo) {
		this.sitNo = sitNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOnHandDate() {
		return onHandDate;
	}

	public void setOnHandDate(String onHandDate) {
		this.onHandDate = onHandDate;
	}

	public String getGbloc() {
		return gbloc;
	}

	public void setGbloc(String gbloc) {
		this.gbloc = gbloc;
	}

	public String getDestinationGbloc() {
		return destinationGbloc;
	}

	public void setDestinationGbloc(String destinationGbloc) {
		this.destinationGbloc = destinationGbloc;
	}

	public String getItemsPieces() {
		return itemsPieces;
	}

	public void setItemsPieces(String itemsPieces) {
		this.itemsPieces = itemsPieces;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getStoredAt() {
		return storedAt;
	}

	public void setStoredAt(String storedAt) {
		this.storedAt = storedAt;
	}

	public String getOnHandContentSeq() {
		return onHandContentSeq;
	}

	public void setOnHandContentSeq(String onHandContentSeq) {
		this.onHandContentSeq = onHandContentSeq;
	}

	@Override
	public String toString() {
		return "GBL [seq=" + seq + ", no=" + no + ", customerName="
				+ customerName + ", pud=" + pud + ", rank=" + rank + ", code="
				+ code + ", scac=" + scac + ", originAddress=" + originAddress
				+ ", ssn=" + ssn + ", areaLocal=" + areaLocal + ", phoneNo="
				+ phoneNo + ", email=" + email + ", rdd=" + rdd + ", vessel="
				+ vessel + ", pcs=" + pcs + ", lbs=" + lbs + ", cuft=" + cuft
				+ ", usNo=" + usNo + ", pod=" + pod + ", poe=" + poe
				+ ", consoleCompany=" + consoleCompany + ", containerNo="
				+ containerNo + ", hbBookingNo=" + hbBookingNo + ", clpNo="
				+ clpNo + ", sealNo=" + sealNo + ", originPort=" + originPort
				+ ", destPort=" + destPort + ", destState=" + destState
				+ ", export=" + export + ", originGBlock=" + originGBlock
				+ ", destGBlock=" + destGBlock + ", originCity=" + originCity
				+ ", milSVC=" + milSVC + ", gblFileNo=" + gblFileNo
				+ ", truckCheck=" + truckCheck + ", bookingCheck="
				+ bookingCheck + ", truckSeq=" + truckSeq + ", bookingSeq="
				+ bookingSeq + ", etd=" + etd + ", eta=" + eta + ", blNo="
				+ blNo + ", seperateFlag=" + seperateFlag + ", remarkRdd="
				+ remarkRdd + ", remarkTac=" + remarkTac + ", consignee="
				+ consignee + ", gblNo=" + gblNo + ", shipperName="
				+ shipperName + ", tsp=" + tsp + ", destAddress=" + destAddress
				+ ", arriveDate=" + arriveDate + ", awbNo=" + awbNo
				+ ", pmjDate=" + pmjDate + ", fright=" + fright
				+ ", eMailAddress=" + eMailAddress + ", oblNo=" + oblNo
				+ ", vessle=" + vessle + ", blCompany=" + blCompany
				+ ", remark=" + remark + ", sitIn=" + sitIn + ", sitOut="
				+ sitOut + ", sitNo=" + sitNo + ", rate=" + rate + ", yjNo="
				+ yjNo + ", totalPcs=" + totalPcs + ", phone=" + phone
				+ ", address=" + address + ", onHandDate=" + onHandDate
				+ ", gbloc=" + gbloc + ", destinationGbloc=" + destinationGbloc
				+ ", itemsPieces=" + itemsPieces + ", grossWeight="
				+ grossWeight + ", netWeight=" + netWeight + ", weightIb="
				+ weightIb + ", attachments=" + Arrays.toString(attachments)
				+ ", attachmentList=" + attachmentList + ", deleteAttachments="
				+ Arrays.toString(deleteAttachments) + "]";
	}

	public Integer getTruckSeq() {
		return truckSeq;
	}

	public void setTruckSeq(Integer truckSeq) {
		this.truckSeq = truckSeq;
	}

	public Integer getBookingSeq() {
		return bookingSeq;
	}

	public void setBookingSeq(Integer bookingSeq) {
		this.bookingSeq = bookingSeq;
	}

	/**
	 * Files to upload
	 */
	private CommonsMultipartFile attachments[];

	/**
	 * Files already uploaded
	 */
	private List<GBLAttachment> attachmentList;

	/**
	 * 
	 */
	private Integer deleteAttachments[];

	public CommonsMultipartFile[] getAttachments() {
		return attachments;
	}

	public void setAttachments(CommonsMultipartFile[] attachments) {
		this.attachments = attachments;
	}

	public List<GBLAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<GBLAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public Integer[] getDeleteAttachments() {
		return deleteAttachments;
	}

	public void setDeleteAttachments(Integer[] deleteAttachments) {
		this.deleteAttachments = deleteAttachments;
	}

	public Boolean getBookingCheck() {
		return bookingCheck;
	}

	public void setBookingCheck(Boolean bookingCheck) {
		this.bookingCheck = bookingCheck;
	}

	public Boolean getHouseCheck() {
		return houseCheck;
	}

	public void setHouseCheck(Boolean houseCheck) {
		this.houseCheck = houseCheck;
	}

	public Integer getHouseSeq() {
		return houseSeq;
	}

	public void setHouseSeq(Integer houseSeq) {
		this.houseSeq = houseSeq;
	}

}
