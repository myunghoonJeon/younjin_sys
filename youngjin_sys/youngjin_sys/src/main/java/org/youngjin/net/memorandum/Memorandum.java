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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memorandum [seq : ").append(seq).append(" ;\ntype : ")
				.append(type).append(" ;\nsubject : ").append(subject)
				.append(" ;\ncomment : ").append(comment).append(" ;\narea : ")
				.append(area).append(" ;\narticles : ").append(articles)
				.append(" ;\narticleComment : ").append(articleComment)
				.append(" ;\nchiefOfOffice : ").append(chiefOfOffice)
				.append(" ;\nofficeInfo : ").append(officeInfo)
				.append(" ;\nareaDirector : ").append(areaDirector)
				.append(" ;\ngblSeq : ").append(gblSeq).append(" ]");
		return builder.toString();
	}

}
