package org.youngjin.net.invoice;

import java.util.List;

public class InvoiceCollection {
	private Integer seq;
	private String invoiceNo;
	private String amount;
	private String state;
	private String net;
	private String remark;
	private String difference;
	private Integer invoiceSeq;
	private String date;
	private String tempNet;
	private List<InvoiceCollectionFlow> invoiceColltionFlowList;
	
	public String getTempNet() {
		return tempNet;
	}

	public void setTempNet(String tempNet) {
		this.tempNet = tempNet;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}
	
	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getInvoiceSeq() {
		return invoiceSeq;
	}

	public void setInvoiceSeq(Integer invoiceSeq) {
		this.invoiceSeq = invoiceSeq;
	}

	public List<InvoiceCollectionFlow> getInvoiceColltionFlowList() {
		return invoiceColltionFlowList;
	}

	public void setInvoiceColltionFlowList(
			List<InvoiceCollectionFlow> invoiceColltionFlowList) {
		this.invoiceColltionFlowList = invoiceColltionFlowList;
	}

	@Override
	public String toString() {
		return "InvoiceCollection [seq=" + seq + ", invoiceNo=" + invoiceNo
				+ ", amount=" + amount + ", state=" + state + ", net=" + net
				+ ", remark=" + remark + ", difference=" + difference
				+ ", invoiceSeq=" + invoiceSeq + ", invoiceColltionFlowList="
				+ invoiceColltionFlowList + "]";
	}

}
