package org.youngjin.net.invoice;

public class InvoiceCollection {
	private Integer seq;
	private String status;
	private String amount;
	private String remark;
	private Integer invoiceSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	@Override
	public String toString() {
		return "InvoiceCollection [seq=" + seq + ", status=" + status
				+ ", amount=" + amount + ", remark=" + remark + ", invoiceSeq="
				+ invoiceSeq + "]";
	}

}
