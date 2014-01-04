package org.youngjin.net.invoice;

public class InvoiceGbl {
	private Integer seq;
	private Integer invoiceListSeq;
	private Integer gblSeq;
	private String gblNo;
	private String rank;
	private String name;
	private String amount;
	private Boolean complete;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getInvoiceListSeq() {
		return invoiceListSeq;
	}

	public void setInvoiceListSeq(Integer invoiceListSeq) {
		this.invoiceListSeq = invoiceListSeq;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "InvoiceGbl [seq=" + seq + ", invoiceListSeq=" + invoiceListSeq
				+ ", gblSeq=" + gblSeq + ", gblNo=" + gblNo + ", rank=" + rank
				+ ", name=" + name + ", amount=" + amount + ", complete="
				+ complete + "]";
	}

}
