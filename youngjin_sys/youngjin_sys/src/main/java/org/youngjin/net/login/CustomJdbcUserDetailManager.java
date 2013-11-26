package org.youngjin.net.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.youngjin.net.util.DateUtil;

public class CustomJdbcUserDetailManager extends JdbcUserDetailsManager {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	// ~ Static Query
	private static final String LOAD_USERS_BY_USERNAME_QUERY = ""
			+ "SELECT u.username, u.password, u.enabled, DATE_FORMAT(u.salt, '%Y-%m-%d %H:%i:%s') AS salt, u.familyname, u.firstname, u.auth, u.area "
			+ "FROM users u "
			+ "WHERE u.username = ? "; 
	private static final String LOAD_GROUP_AUTHORITIES_QUERY = ""
			+ "SELECT ga.authority "
			+ "FROM users u, groups g, group_authorities ga "
			+ "WHERE u.username = ? AND g.id = ga.group_id AND g.group_name = u.auth ";
	
	
	// ~ Dynamic Injection
	
	private LoginDao loginDao;
	private SaltSource saltSource;
	private PasswordEncoder passwordEncoder;
	
	
	@Inject
	private void config(LoginDao loginDao, SaltSource saltSource,
			PasswordEncoder passwordEncoder) {
		this.loginDao = loginDao;
		this.saltSource = saltSource;
		this.passwordEncoder = passwordEncoder;
	}
	
	protected UserDetails createUserDetails(String id,
			UserDetailsYoungjin userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		
		String returnUserid = userFromUserQuery.getUsername();
		if (!isUsernameBasedPrimaryKey()) {
			returnUserid = id;
		}

		return new User(returnUserid, userFromUserQuery.getPassword(),
				((User) userFromUserQuery).getSalt(),
				userFromUserQuery.getName(), userFromUserQuery.isEnabled(),
				userFromUserQuery.getAuth(), userFromUserQuery.getArea(), combinedAuthorities);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user " + username);

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"), username);
		}

		UserDetailsYoungjin user = (UserDetailsYoungjin) users.get(0);

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(
				dbAuthsSet);
		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User " + username + " has no authorities");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"), username);
		}

		return createUserDetails(username, user, dbAuths);
	}
	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(LOAD_USERS_BY_USERNAME_QUERY,
				new String[] { username }, new RowMapper<UserDetails>() {
					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String id = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						String salt = rs.getString(4);
						String familyName = rs.getString(5);
						String firstName = rs.getString(6);
						Integer auth = rs.getInt(7);
						Integer area = rs.getInt(8);
						logger.debug("LoadUserByUsername " + auth
								+ " Username " + id);
						return new User(id, password, salt, firstName + " "
								+ familyName, enabled, auth, area,
								AuthorityUtils.NO_AUTHORITIES);
					}
				});
	}	

	@Override
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		return getJdbcTemplate().query(LOAD_GROUP_AUTHORITIES_QUERY,
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String roleName = "ROLE_" + rs.getString(1);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	public User createUser() {
		User user = new User();		
		user.setUsername(getCreateUserName());		
		setEncodedPassword(user, user.getPassword());
		
		loginDao.insertUser(user);
		
		if(user.getAuth() != 0){
			loginDao.insertGroups(user);
			loginDao.insertGroupAuthorities(user);
		}
		
		return user;
	}
	
	public void updateUserByAdmin(User user) {
		loginDao.updateUserByAdmin(user);
	}	
	
	public void deleteUserByAdmin(Integer seq) {
		loginDao.deleteUserBySeq(seq);
	}
	
	public void setEncodedPassword(User user, String password) {
		user.setSalt(DateUtil.getSysdate("yyyy-MM-dd HH:mm:ss"));

		String encodedPassword = passwordEncoder.encodePassword(password,
				saltSource.getSalt(user));
		user.setPassword(encodedPassword);
	}

	public void changePassword(User user) {
		if (confirmPassword(user)) {
			setEncodedPassword(user, user.getNewPassword());
			user.setLastUpdateBy(user.getUsername());
			loginDao.updatePassword(user);
		}
	}
	
	public boolean confirmPassword(User user) {
		String username = user.getUsername();
		String password = user.getPassword();

		User confirmUserInfo = (User) loadUserByUsername(username);
		String encodedPassword = passwordEncoder.encodePassword(password,
				saltSource.getSalt(confirmUserInfo));
		return encodedPassword.equals(confirmUserInfo.getPassword()) ? false
				: true;
	}

	private String getCreateUserName() {
		String lastUserName = loginDao.getUserIndex();
		Integer userIndex = 0;
		if( lastUserName != null && lastUserName.contains("youngjin")){
			userIndex = Integer.parseInt(lastUserName.substring(8, lastUserName.length())) + 1;
		}
		
		return "youngjin" + userIndex;
	}
}
