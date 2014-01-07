package org.youngjin.net;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GBL {
	// 공통
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

	// outbound
	private String usNo;
	private String pod;
	private String poe;
	private String consoleCompany;
	private Integer hbBookingNo;
	private String clpNo;
	private String sealNo;
	private String originPort;
	private String destPort;
	private String destState;

	private String originGBlock;
	private String destGBlock;

	private String originCity;

	private String milSVC;

	private Integer gblFileNo;

	private Boolean truckCheck;
	private Boolean bookingCheck;

	private Integer truckSeq;
	private Integer bookingSeq;

	private String etd;
	private String eta;
	private String blNo;
	
	private Boolean seperateFlag;

	// inbound
	private String gross;
	private String net;
	private String tare;
	private String itemEa;
	private String arriveDate;
	private String oblNo;
	private String pmjDate;
	private String remark;

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

	public Integer getHbBookingNo() {
		return hbBookingNo;
	}

	public void setHbBookingNo(Integer hbBookingNo) {
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
	
	//inbound


	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getTare() {
		return tare;
	}

	public void setTare(String tare) {
		this.tare = tare;
	}

	public String getItemEa() {
		return itemEa;
	}

	public void setItemEa(String itemEa) {
		this.itemEa = itemEa;
	}

	public String getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getOblNo() {
		return oblNo;
	}

	public void setOblNo(String oblNo) {
		this.oblNo = oblNo;
	}

	public String getPmjDate() {
		return pmjDate;
	}

	public void setPmjDate(String pmjDate) {
		this.pmjDate = pmjDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
				+ ", consoleCompany=" + consoleCompany + ", hbBookingNo="
				+ hbBookingNo + ", clpNo=" + clpNo + ", sealNo=" + sealNo
				+ ", originPort=" + originPort + ", destPort=" + destPort
				+ ", destState=" + destState + ", originGBlock=" + originGBlock
				+ ", destGBlock=" + destGBlock + ", originCity=" + originCity
				+ ", milSVC=" + milSVC + ", gblFileNo=" + gblFileNo
				+ ", truckCheck=" + truckCheck + ", bookingCheck="
				+ bookingCheck + ", truckSeq=" + truckSeq + ", bookingSeq="
				+ bookingSeq + ", etd=" + etd + ", eta=" + eta + ", blNo="
				+ blNo + ", seperateFlag=" + seperateFlag + ", gross=" + gross
				+ ", net=" + net + ", tare=" + tare + ", itemEa=" + itemEa
				+ ", arriveDate=" + arriveDate + ", oblNo=" + oblNo
				+ ", pmjDate=" + pmjDate + ", remark=" + remark
				+ ", attachments=" + Arrays.toString(attachments)
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
}
