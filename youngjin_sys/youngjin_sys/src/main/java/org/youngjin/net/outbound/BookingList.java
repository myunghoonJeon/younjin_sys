package org.youngjin.net.outbound;

import java.util.List;

public class BookingList {
	private Integer seq;
	private String gblNo;
	private Integer gblSeq;
	private String memo;
	private String remark;
	private String writeUser;
	private String writeDate;
	private Integer yjCount;
	private String gblList;
	private String yjnInput;
	private String yjnInput2;
	private String cfs;
	
	public String getYjnInput2() {
		return yjnInput2;
	}

	public void setYjnInput2(String yjnInput2) {
		this.yjnInput2 = yjnInput2;
	}

	public String getCfs() {
		return cfs;
	}

	public void setCfs(String cfs) {
		this.cfs = cfs;
	}

	public String getYjnInput() {
		return yjnInput;
	}

	public void setYjnInput(String yjnInput) {
		this.yjnInput = yjnInput;
	}

	public String getGblList() {
		return gblList;
	}

	public void setGblList(String gblList) {
		this.gblList = gblList;
	}

	public Integer getYjCount() {
		return yjCount;
	}

	public void setYjCount(Integer yjCount) {
		this.yjCount = yjCount;
	}

	private List<Weightcertificate> weightcertificateList;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWriteUser() {
		return writeUser;
	}

	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public List<Weightcertificate> getWeightcertificateList() {
		return weightcertificateList;
	}

	public void setWeightcertificateList(
			List<Weightcertificate> weightcertificateList) {
		this.weightcertificateList = weightcertificateList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookingList [seq : ").append(seq)
				.append(" ;\ngblNo : ").append(gblNo).append(" ;\ngblSeq : ")
				.append(gblSeq).append(" ;\nmemo : ").append(memo)
				.append(" ;\nremark : ").append(remark)
				.append(" ;\nwriteUser : ").append(writeUser)
				.append(" ;\nwriteDate : ").append(writeDate)
				.append(" ;\nweightcertificateList : ")
				.append(weightcertificateList).append(" ]");
		return builder.toString();
	}

}
