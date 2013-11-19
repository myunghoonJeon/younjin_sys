package org.youngjin.net.login;

/**
 * @author Kten
 * 
 */
public enum Authority {

	ANONYMOUS(0), NORMAL(3), MANAGER(2), ADMIN(1);

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
			return "ANONYMOUS";
		case 3:
			return "NORMAL";
		case 2:
			return "MANAGER";
		case 1:
			return "ADMIN";
		default:
			return null;
		}
	}
}
