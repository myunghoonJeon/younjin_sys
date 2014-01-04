package org.youngjin.net.invoice;

public class InvoiceGblContent {
	private Integer seq;
	private Integer invoiceGblSeq;
	private String chargingItem;
	private String quantity;
	private String amount;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getInvoiceGblSeq() {
		return invoiceGblSeq;
	}

	public void setInvoiceGblSeq(Integer invoiceGblSeq) {
		this.invoiceGblSeq = invoiceGblSeq;
	}

	public String getChargingItem() {
		return chargingItem;
	}

	public void setChargingItem(String chargingItem) {
		this.chargingItem = chargingItem;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "InvoiceGblContent [seq=" + seq + ", invoiceGblSeq="
				+ invoiceGblSeq + ", chargingItem=" + chargingItem
				+ ", quantity=" + quantity + ", amount=" + amount + "]";
	}

}
