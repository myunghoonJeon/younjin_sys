package org.youngjin.net.outbound;

public class House {
	private Integer seq;
	private String contNo;
	private String sealNo;
	private String consignee;
	private String vessel;
	private String voyageNo;
	private String carrierBookingNo;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyageNo() {
		return voyageNo;
	}

	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}

	public String getCarrierBookingNo() {
		return carrierBookingNo;
	}

	public void setCarrierBookingNo(String carrierBookingNo) {
		this.carrierBookingNo = carrierBookingNo;
	}

	@Override
	public String toString() {
		return "House [seq=" + seq + ", contNo=" + contNo + ", sealNo="
				+ sealNo + ", consignee=" + consignee + ", vessel=" + vessel
				+ ", voyageNo=" + voyageNo + "]";
	}

}
