package org.youngjin.net.invoice;

public class Invoice {
	private Integer seq;
	private String tsp;
	private String startDate;
	private String endDate;
	private String writeDate;
	private Boolean complete;
	private String process;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return "Invoice [seq=" + seq + ", tsp=" + tsp + ", startDate="
				+ startDate + ", endDate=" + endDate + ", writeDate="
				+ writeDate + ", complete=" + complete + ", process=" + process
				+ "]";
	}

}
