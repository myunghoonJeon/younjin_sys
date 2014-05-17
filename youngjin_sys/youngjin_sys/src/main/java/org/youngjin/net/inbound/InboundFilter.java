package org.youngjin.net.inbound;

import org.youngjin.net.util.AbstractListFilter;

public class InboundFilter extends AbstractListFilter {
	private int tempseq;
	public int getTempseq() {
		return tempseq;
	}

	public void setTempseq(int tempseq) {
		this.tempseq = tempseq;
	}

	private String branch;
	private String carrier;
	private String code;

	private String startPud;
	private String endPud;

	private String area;

	private String searchTitle;

	private String searchContent;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStartPud() {
		return startPud;
	}

	public void setStartPud(String startPud) {
		this.startPud = startPud;
	}

	public String getEndPud() {
		return endPud;
	}

	public void setEndPud(String endPud) {
		this.endPud = endPud;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	@Override
	public String toString() {
		return "InboundFilter [branch=" + branch + ", carrier=" + carrier
				+ ", code=" + code + ", startPud=" + startPud + ", endPud="
				+ endPud + ", area=" + area + ", searchTitle=" + searchTitle
				+ "]";
	}
}
