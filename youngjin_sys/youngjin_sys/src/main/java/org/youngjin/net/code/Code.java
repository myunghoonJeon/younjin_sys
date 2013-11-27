package org.youngjin.net.code;

public class Code {
	private String mainCode;
	private String subCode;
	private String codeName;
	private String codeEtc;

	public String getMainCode() {
		return mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeEtc() {
		return codeEtc;
	}

	public void setCodeEtc(String codeEtc) {
		this.codeEtc = codeEtc;
	}

	@Override
	public String toString() {
		return "Code [mainCode=" + mainCode + ", subCode=" + subCode
				+ ", codeName=" + codeName + ", codeEtc=" + codeEtc + "]";
	}

}
