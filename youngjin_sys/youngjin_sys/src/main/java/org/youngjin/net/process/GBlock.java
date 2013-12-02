package org.youngjin.net.process;

public class GBlock {
	private Integer seq;
	private String dodaac;
	private String gblock;
	private String usNo;
	private String remark;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDodaac() {
		return dodaac;
	}

	public void setDodaac(String dodaac) {
		this.dodaac = dodaac;
	}

	public String getUsNo() {
		return usNo;
	}

	public void setUsNo(String usNo) {
		this.usNo = usNo;
	}

	public String getGblock() {
		return gblock;
	}

	public void setGblock(String gblock) {
		this.gblock = gblock;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GBlock [seq : ").append(seq).append(" ;\ndodaac : ")
				.append(dodaac).append(" ;\ngblock : ").append(gblock)
				.append(" ;\nusNo : ").append(usNo).append(" ;\nremark : ")
				.append(remark).append(" ]");
		return builder.toString();
	}

}
