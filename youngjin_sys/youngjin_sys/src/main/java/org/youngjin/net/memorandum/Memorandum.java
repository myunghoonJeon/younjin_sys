package org.youngjin.net.memorandum;

public class Memorandum {
	private Integer seq;
	private String type;
	private String subject;
	private String comment;
	private String area;
	private String articles;
	private String articleComment1;
	private String articleComment2;
	private String articleComment3;
	private String articleComment4;
	private String articleComment5;
	private Integer gblSeq;

	private String writeDate;
	
	private String extraPickUpCharge;
	private String termination;
	private String sitNo;
	public String getSitNo() {
		return sitNo;
	}

	public void setSitNo(String sitNo) {
		this.sitNo = sitNo;
	}

	private String sitStartDate;
	private String sitEndDate;
	private String longCarry;

	private String invoiceValue;

	private Integer memorandumSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	public String getArticleComment1() {
		return articleComment1;
	}

	public void setArticleComment1(String articleComment1) {
		this.articleComment1 = articleComment1;
	}

	public String getArticleComment2() {
		return articleComment2;
	}

	public void setArticleComment2(String articleComment2) {
		this.articleComment2 = articleComment2;
	}

	public String getArticleComment3() {
		return articleComment3;
	}

	public void setArticleComment3(String articleComment3) {
		this.articleComment3 = articleComment3;
	}

	public String getArticleComment4() {
		return articleComment4;
	}

	public void setArticleComment4(String articleComment4) {
		this.articleComment4 = articleComment4;
	}

	public String getArticleComment5() {
		return articleComment5;
	}

	public void setArticleComment5(String articleComment5) {
		this.articleComment5 = articleComment5;
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

	public Integer getMemorandumSeq() {
		return memorandumSeq;
	}

	public void setMemorandumSeq(Integer memorandumSeq) {
		this.memorandumSeq = memorandumSeq;
	}

	public String getExtraPickUpCharge() {
		return extraPickUpCharge;
	}

	public void setExtraPickUpCharge(String extraPickUpCharge) {
		this.extraPickUpCharge = extraPickUpCharge;
	}

	public String getSitStartDate() {
		return sitStartDate;
	}

	public void setSitStartDate(String sitStartDate) {
		this.sitStartDate = sitStartDate;
	}

	public String getSitEndDate() {
		return sitEndDate;
	}

	public void setSitEndDate(String sitEndDate) {
		this.sitEndDate = sitEndDate;
	}

	public String getLongCarry() {
		return longCarry;
	}

	public void setLongCarry(String longCarry) {
		this.longCarry = longCarry;
	}

	public String getTermination() {
		if(this.termination != null)
			return termination;
		else 
			return "0";
	}

	public void setTermination(String termination) {
		this.termination = termination;
	}

	public String getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public String[] getArticleList() {
		return this.articles.split(",");
	}

	@Override
	public String toString() {
		return "Memorandum [seq=" + seq + ", type=" + type + ", subject="
				+ subject + ", comment=" + comment + ", area=" + area
				+ ", articles=" + articles + ", articleComment1="
				+ articleComment1 + ", articleComment2=" + articleComment2
				+ ", articleComment3=" + articleComment3 + ", articleComment4="
				+ articleComment4 + ", articleComment5=" + articleComment5
				+ ", gblSeq=" + gblSeq + ", writeDate=" + writeDate
				+ ", extraPickUpCharge=" + extraPickUpCharge + ", termination="
				+ termination + ", sitStartDate=" + sitStartDate
				+ ", sitEndDate=" + sitEndDate + ", longCarry=" + longCarry
				+ ", invoiceValue=" + invoiceValue + ", memorandumSeq=" + memorandumSeq
				+ ",sitNo="+sitNo+ "]";
	}

}
