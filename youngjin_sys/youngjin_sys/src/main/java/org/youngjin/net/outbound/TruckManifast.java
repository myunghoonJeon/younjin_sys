package org.youngjin.net.outbound;

public class TruckManifast {
	private Integer seq;
	private String date;
	private String branch;
	private String code;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "TruckManifast [seq=" + seq + ", date=" + date + ", branch="
				+ branch + ", code=" + code + "]";
	}
}
