package org.youngjin.net.memorandum;

public class MemorandumList {

	private Integer seq;
	private Integer gblSeq;
	private String writeDate;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "MemorandumList [seq=" + seq + ", gblSeq=" + gblSeq
				+ ", writeDate=" + writeDate + "]";
	}

}
