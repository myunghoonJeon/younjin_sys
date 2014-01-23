package org.youngjin.net.login;

/**
 * @author Kten
 * 
 */
public enum Authority {

	LEVEL1(0), LEVEL2(3), LEVEL3(2), LEVEL4(1);

	private int value;

	Authority(int value) {
		this.value = value;
	}

	public int getAuthority() {
		return this.value;
	}
	
	public void setAuthority(int value){
		this.value = value;
	}

	public String getStrAuthority() {
		switch (this.value) {
		case 0:
			return "LEVEL1";
		case 3:
			return "LEVEL2";
		case 2:
			return "LEVEL3";
		case 1:
			return "LEVEL4";
		default:
			return null;
		}
	}
}
