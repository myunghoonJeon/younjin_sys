package org.youngjin.net.memorandum;

public class Memorandum {
	private Integer seq;
	private String type;
	private String subject;
	private String comment;
	private String area;
	private String articles;
	private String articleComment;
	private String chiefOfOffice;
	private String officeInfo;
	private String areaDirector;
	private Integer gblSeq;

	private String writeDate;
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

	public String getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(String articleComment) {
		this.articleComment = articleComment;
	}

	public String getChiefOfOffice() {
		return chiefOfOffice;
	}

	public void setChiefOfOffice(String chiefOfOffice) {
		this.chiefOfOffice = chiefOfOffice;
	}

	public String getOfficeInfo() {
		return officeInfo;
	}

	public void setOfficeInfo(String officeInfo) {
		this.officeInfo = officeInfo;
	}

	public String getAreaDirector() {
		return areaDirector;
	}

	public void setAreaDirector(String areaDirector) {
		this.areaDirector = areaDirector;
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

	@Override
	public String toString() {
		return "Memorandum [seq=" + seq + ", type=" + type + ", subject="
				+ subject + ", comment=" + comment + ", area=" + area
				+ ", articles=" + articles + ", articleComment="
				+ articleComment + ", chiefOfOffice=" + chiefOfOffice
				+ ", officeInfo=" + officeInfo + ", areaDirector="
				+ areaDirector + ", gblSeq=" + gblSeq + ", writeDate="
				+ writeDate + ", memorandumSeq=" + memorandumSeq + "]";
	}

}
