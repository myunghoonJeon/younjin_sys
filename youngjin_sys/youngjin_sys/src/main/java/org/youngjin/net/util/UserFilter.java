package org.youngjin.net.util;

import java.util.List;

/**
 * @author Kten
 * 
 */
public class UserFilter extends AbstractListFilter {

	/**
	 * SortOrder default : seq
	 */
	private String sortOrder = "seq";

	/**
	 * SortDesc default : desc
	 */
	private String sortDesc = "DESC";

	private String familyName;

	private String firstName;

	private Integer auth;

	public Integer getAuth() {
		return auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSortDesc() {
		return sortDesc;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
