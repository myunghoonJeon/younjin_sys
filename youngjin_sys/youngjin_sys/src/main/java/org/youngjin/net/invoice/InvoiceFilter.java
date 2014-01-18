package org.youngjin.net.invoice;

import org.youngjin.net.util.AbstractListFilter;

public class InvoiceFilter extends AbstractListFilter {

	private String process;

	public String getProcess() {
		return process;
	}

	public String tsp;

	public String startDate;
	public String endDate;

	public String gblNo;

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "InvoiceFilter [process=" + process + ", tsp=" + tsp
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", gblNo=" + gblNo + "]";
	}

}
