package org.youngjin.net.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class User extends AbstractUser {
	private Integer seq;

	private String newPassword = "dudwls!";

	private String firstName;

	private String familyName;

	private String lastUpdateBy;

	private String lastUpdate;

	private Integer authId;

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
		if (super.getAuth().equals(Authority.NORMAL.getAuthority()))
			return Authority.NORMAL.getStrAuthority();
		else if (super.getAuth().equals(Authority.MANAGER.getAuthority()))
			return Authority.MANAGER.getStrAuthority();
		else if (super.getAuth().equals(Authority.ADMIN.getAuthority()))
			return Authority.ADMIN.getStrAuthority();
		else
			return Authority.ANONYMOUS.getStrAuthority();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
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
