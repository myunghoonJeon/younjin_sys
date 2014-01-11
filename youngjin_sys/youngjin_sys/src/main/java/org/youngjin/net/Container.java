package org.youngjin.net;

public class Container {
	private Integer seq;
	private String status;
	private int count;
	private String remark;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Container [seq=" + seq + ", status=" + status + ", count="
				+ count + ", remark=" + remark + "]";
	}

}
