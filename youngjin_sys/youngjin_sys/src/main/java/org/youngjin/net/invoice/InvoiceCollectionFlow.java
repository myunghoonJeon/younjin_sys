package org.youngjin.net.invoice;

public class InvoiceCollectionFlow {
	private Integer seq;
	private String amount;
	private String state;
	private String remark;
	private String writeDate;

	private Integer invoiceCollectionSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getInvoiceCollectionSeq() {
		return invoiceCollectionSeq;
	}

	public void setInvoiceCollectionSeq(Integer invoiceCollectionSeq) {
		this.invoiceCollectionSeq = invoiceCollectionSeq;
	}

	@Override
	public String toString() {
		return "InvoiceCollectionFlow [seq=" + seq + ", amount=" + amount
				+ ", state=" + state + ", remark=" + remark + ", writeDate="
				+ writeDate + ", invoiceCollectionSeq=" + invoiceCollectionSeq
				+ "]";
	}

}
