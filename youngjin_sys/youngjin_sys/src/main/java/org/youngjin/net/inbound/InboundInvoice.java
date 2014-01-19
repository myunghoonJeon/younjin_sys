package org.youngjin.net.inbound;

import java.util.List;

public class InboundInvoice {
	private Integer seq;
	private String gblNo;
	private String name;
	private String ssn;
	private String inboundInvoiceNo;
	private String invoiceDate;
	private Integer gblSeq;

	private String rank;
	private String eta;
	private Boolean delcarationCheck;

	private List<WeightIb> weightList;

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getInboundInvoiceNo() {
		return inboundInvoiceNo;
	}

	public void setInboundInvoiceNo(String inboundInvoiceNo) {
		this.inboundInvoiceNo = inboundInvoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public List<WeightIb> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<WeightIb> weightList) {
		this.weightList = weightList;
	}

	public Integer getSeq() {
		return seq;
	}

	public Boolean getDelcarationCheck() {
		return delcarationCheck;
	}
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}


	public void setDelcarationCheck(Boolean delcarationCheck) {
		this.delcarationCheck = delcarationCheck;
	}

	@Override
	public String toString() {
		return "InboundInvoice [seq=" + seq + ", gblNo=" + gblNo + ", name="
				+ name + ", ssn=" + ssn + ", inboundInvoiceNo="
				+ inboundInvoiceNo + ", invoiceDate=" + invoiceDate
				+ ", gblSeq=" + gblSeq + ", rank=" + rank + ", eta=" + eta
				+ ", delcarationCheck=" + delcarationCheck + ", weightList="
				+ weightList + "]";
	}
}
