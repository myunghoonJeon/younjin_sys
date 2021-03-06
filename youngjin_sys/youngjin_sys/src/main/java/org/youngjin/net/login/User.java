package org.youngjin.net.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class User extends AbstractUser {
	private Integer seq;
	private String newId="";
	private String newPassword = "2222";
	
	private String firstName;

	private String familyName;

	private String lastUpdateBy;

	private String lastUpdate;

	private Integer authId;

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

	private String process = "home";

	private String subProcess = "";

	public User() {
		super();
	}

	public User(String username, String password, String salt, String name,
			boolean enabled, Integer auth, Integer area,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, salt, name, enabled, auth, area, true, true,
				true, authorities);
	}

	public User(String username, String password, String salt, String name,
			boolean enabled, Integer auth, Integer area, String areaStr,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, salt, name, enabled, auth, area, areaStr,
				true, true, true, authorities);
	}

	public User(String username, String password, String salt, String name,
			boolean enabled, Integer auth, Integer area,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, salt, name, enabled, auth, area, "",
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
	}

	public User(String username, String password, String salt, String name,
			boolean enabled, Integer auth, Integer area, String areaStr,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, salt, name, enabled, auth, area, areaStr,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getProcess() {
		return process;
	}

	public String getSubProcess() {
		return subProcess;
	}

	public void setSubProcess(String subProcess) {
		this.subProcess = subProcess;
	}

	public void setProcess(String process) {
		if (!"form".equals(process))
			this.process = process;
	}

	public String getAuthStr() {
		if (super.getAuth().equals(Authority.LEVEL2.getAuthority()))
			return Authority.LEVEL2.getStrAuthority();
		else if (super.getAuth().equals(Authority.LEVEL3.getAuthority()))
			return Authority.LEVEL3.getStrAuthority();
		else if (super.getAuth().equals(Authority.LEVEL4.getAuthority()))
			return Authority.LEVEL4.getStrAuthority();
		else
			return Authority.LEVEL1.getStrAuthority();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("User [seq : ").append(seq).append(" ;\nnewPassword : ")
				.append(newPassword).append(" ;\nfirstName : ")
				.append(firstName).append(" ;\nfamilyName : ")
				.append(familyName).append(" ;\nlastUpdateBy : ")
				.append(lastUpdateBy).append(" ;\nlastUpdate : ")
				.append(lastUpdate).append(" ;\nauthId : ").append(authId)
				.append(" ;\nprocess : ").append(process)
				.append(" ;\nsubProcess : ").append(subProcess).append(" ]");
		return builder.toString();
	}
}
