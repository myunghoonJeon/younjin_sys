package org.youngjin.net.inbound;

import java.util.List;

import org.youngjin.net.util.CalcUtil;

public class InboundInvoice {
	private Integer seq;
	private String gblNo;
	private String name;
	private String ssn;
	private String inboundInvoiceNo;
	private String invoiceDate;
	private Integer gblSeq;

	private String rank;
	private String eta;
	private Boolean delcarationCheck;
	private String writeDate;
	private String oblNo;
	private String awbNo;
	private String vessle;
	private String fright;
	private String code;

	private String piece;
	private String cuft;
	private String grossWt;
	private String netWt;
	private String itemsPieces;

	private Integer onHandListContentSeq;

	private List<WeightIb> weightList;

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getInboundInvoiceNo() {
		return inboundInvoiceNo;
	}

	public void setInboundInvoiceNo(String inboundInvoiceNo) {
		this.inboundInvoiceNo = inboundInvoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public List<WeightIb> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<WeightIb> weightList) {
		this.weightList = weightList;
	}

	public Integer getSeq() {
		return seq;
	}

	public Boolean getDelcarationCheck() {
		return delcarationCheck;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public void setDelcarationCheck(Boolean delcarationCheck) {
		this.delcarationCheck = delcarationCheck;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getOblNo() {
		return oblNo;
	}

	public void setOblNo(String oblNo) {
		this.oblNo = oblNo;
	}

	public String getVessle() {
		return vessle;
	}

	public void setVessle(String vessle) {
		this.vessle = vessle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOnHandListContentSeq() {
		return onHandListContentSeq;
	}

	public void setOnHandListContentSeq(Integer onHandListContentSeq) {
		this.onHandListContentSeq = onHandListContentSeq;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getCuft() {
		return CalcUtil.checkCommaString(cuft);
	}
	
	public String getCbm(){
		return String.format("%.3f", (Integer.parseInt(cuft) / 35.315));
	}

	public void setCuft(String cuft) {
		this.cuft = cuft;
	}

	public String getGrossWt() {
		return CalcUtil.checkCommaString(grossWt);
	}
	
	public String getGrossWtKg(){
		return String.format("%.3f", (Integer.parseInt(grossWt) / 0.45359237));
	}

	public void setGrossWt(String grossWt) {
		this.grossWt = grossWt;
	}

	public String getNetWt() {
		return CalcUtil.checkCommaString(netWt);
	}
	
	public String getNetWtKg(){
		return String.format("%.3f", (Integer.parseInt(netWt) / 0.45359237));
	}

	public void setNetWt(String netWt) {
		this.netWt = netWt;
	}

	public String getAwbNo() {
		return awbNo;
	}

	public void setAwbNo(String awbNo) {
		this.awbNo = awbNo;
	}

	public String getFright() {
		return fright;
	}

	public void setFright(String fright) {
		this.fright = fright;
	}

	public String getItemsPieces() {
		return itemsPieces;
	}

	public void setItemsPieces(String itemsPieces) {
		this.itemsPieces = itemsPieces;
	}

	@Override
	public String toString() {
		return "InboundInvoice [seq=" + seq + ", gblNo=" + gblNo + ", name="
				+ name + ", ssn=" + ssn + ", inboundInvoiceNo="
				+ inboundInvoiceNo + ", invoiceDate=" + invoiceDate
				+ ", gblSeq=" + gblSeq + ", rank=" + rank + ", eta=" + eta
				+ ", delcarationCheck=" + delcarationCheck + ", writeDate="
				+ writeDate + ", oblNo=" + oblNo + ", awbNo=" + awbNo
				+ ", vessle=" + vessle + ", fright=" + fright + ", code="
				+ code + ", piece=" + piece + ", cuft=" + cuft + ", grossWt="
				+ grossWt + ", netWt=" + netWt + ", itemsPieces=" + itemsPieces
				+ ", onHandListContentSeq=" + onHandListContentSeq
				+ ", weightList=" + weightList + "]";
	}
}
