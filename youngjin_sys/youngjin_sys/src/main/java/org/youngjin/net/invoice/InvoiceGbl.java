package org.youngjin.net.invoice;

public class InvoiceGbl {
	private Integer seq;
	private Integer invoiceListSeq;
	private Integer gblSeq;
	private String gblNo;
	private String rank;
	private String name;
	private String code;
	private String amount;
	private Boolean complete;
	
	private String remark;

	private Invoice invoice;

	// invoice List 작성을 위한 param
	private String tsp;
	private String pud;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getInvoiceListSeq() {
		return invoiceListSeq;
	}

	public void setInvoiceListSeq(Integer k) {
		this.invoiceListSeq = k;
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getPud() {
		return pud;
	}

	public void setPud(String pud) {
		this.pud = pud;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "InvoiceGbl [seq=" + seq + ", invoiceListSeq=" + invoiceListSeq
				+ ", gblSeq=" + gblSeq + ", gblNo=" + gblNo + ", rank=" + rank
				+ ", name=" + name + ", code=" + code + ", amount=" + amount
				+ ", complete=" + complete + ", remark=" + remark
				+ ", invoice=" + invoice + ", tsp=" + tsp + ", pud=" + pud
				+ "]";
	}

}
