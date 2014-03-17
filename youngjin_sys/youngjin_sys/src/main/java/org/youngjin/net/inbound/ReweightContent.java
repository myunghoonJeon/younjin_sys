package org.youngjin.net.inbound;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReweightContent {
	private Integer seq;
	private String deliDate;
	private String originGblock;
	private String code;
	private String scacCode;
	private String gblNo;
	private String fullName;
	private String oWt;
	private String rWt;
	private String sWt;
	private String dentn;
	private String rateGbl31;

	private Integer gblSeq;
	private Integer reweightSeq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDeliDate() {
		return deliDate;
	}

	public void setDeliDate(String deliDate) {
		this.deliDate = deliDate;
	}
	
	public String getMonth(){
		SimpleDateFormat format = new SimpleDateFormat("MMM", Locale.UK);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(deliDate.substring(0, 4)), Integer.parseInt(deliDate.substring(4, 6)) - 1, Integer.parseInt(deliDate.substring(6, 8)));
		
		return format.format(calendar.getTime());
	}
	
	public String getCodeStr(){
		if(code.equals("3") || code.equals("4") || code.equals("5") || code.equals("T"))
			return "HHG";
		else
			return "UB";
	}

	public String getOriginGblock() {
		return originGblock;
	}

	public void setOriginGblock(String originGblock) {
		this.originGblock = originGblock;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScacCode() {
		return scacCode;
	}

	public void setScacCode(String scacCode) {
		this.scacCode = scacCode;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String gblNo) {
		this.gblNo = gblNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getoWt() {
		return oWt;
	}

	public void setoWt(String oWt) {
		this.oWt = oWt;
	}

	public String getrWt() {
		return rWt;
	}

	public void setrWt(String rWt) {
		this.rWt = rWt;
	}

	public String getsWt() {
		return sWt;
	}

	public void setsWt(String sWt) {
		this.sWt = sWt;
	}

	public String getDentn() {
		return dentn;
	}

	public void setDentn(String dentn) {
		this.dentn = dentn;
	}

	public String getRateGbl31() {
		return rateGbl31;
	}

	public void setRateGbl31(String rateGbl31) {
		this.rateGbl31 = rateGbl31;
	}

	public Integer getGblSeq() {
		return gblSeq;
	}

	public void setGblSeq(Integer gblSeq) {
		this.gblSeq = gblSeq;
	}

	public Integer getReweightSeq() {
		return reweightSeq;
	}

	public void setReweightSeq(Integer reweightSeq) {
		this.reweightSeq = reweightSeq;
	}

	@Override
	public String toString() {
		return "ReweightContent [seq=" + seq + ", deliDate=" + deliDate
				+ ", originGblock=" + originGblock + ", scacCode=" + scacCode
				+ ", gblNo=" + gblNo + ", fullName=" + fullName + ", oWt="
				+ oWt + ", rWt=" + rWt + ", sWt=" + sWt + ", dentn=" + dentn
				+ ", rateGbl31=" + rateGbl31 + ", gblSeq=" + gblSeq
				+ ", reweightSeq=" + reweightSeq + "]";
	}

}
