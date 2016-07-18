package org.youngjin.net.outbound;

import org.youngjin.net.util.AbstractListFilter;

public class OutboundFilter extends AbstractListFilter {
	private String gblNoForGetOutboundList;
	public String getGblNoForGetOutboundList() {
		return gblNoForGetOutboundList;
	}

	public void setGblNoForGetOutboundList(String gblNoForGetOutboundList) {
		this.gblNoForGetOutboundList = gblNoForGetOutboundList;
	}

	private String branch;
	private String carrier;
	private String code;

	private String startPud;
	private String endPud;

	private String area;

	private Boolean truckManifastFlag = false;
	
	private Boolean houseBlFlag = false;

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

	public Boolean getTruckManifastFlag() {
		return truckManifastFlag;
	}

	public void setTruckManifastFlag(Boolean truckManifastFlag) {
		this.truckManifastFlag = truckManifastFlag;
	}

	public Boolean getHouseBlFlag() {
		return houseBlFlag;
	}

	public void setHouseBlFlag(Boolean houseBlFlag) {
		this.houseBlFlag = houseBlFlag;
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
		return "OutboundFilter [branch=" + branch + ", carrier=" + carrier
				+ ", code=" + code + ", startPud=" + startPud + ", endPud="
				+ endPud + ", area=" + area + ", truckManifastFlag="
				+ truckManifastFlag + ", houseBlFlag=" + houseBlFlag
				+ ", searchTitle=" + searchTitle + ", searchContent="
				+ searchContent + "]";
	}
}
