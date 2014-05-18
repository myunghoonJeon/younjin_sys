package org.youngjin.net.inbound;

public class DeclarationList {
	private int rownum;
	private int seq;
	private String invoiceDate;
	private String writeDate;
	private int gblCount;
	
	public int getGblCount() {
		return gblCount;
	}
	public void setGblCount(int gblCount) {
		this.gblCount = gblCount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}
