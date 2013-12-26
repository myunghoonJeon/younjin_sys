package org.youngjin.net.login;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.youngjin.net.util.UserFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/org/youngjin/net/config/bean/root-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomJdbcUserDetailManagerTest {

	private static Logger logger = LoggerFactory
			.getLogger(CustomJdbcUserDetailManagerTest.class);

	private User user = new User();
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private SaltSource saltSource;

	@Resource
	private CustomJdbcUserDetailManager customJdbcUserDetailManager;
	@Resource
	private LoginDao loginDao;

	@Before
	public void BEFORE() {
		user.setUsername("rhkdduf63");
		user.setPassword("qkr4062988!");
		user.setFirstName("Park");
		user.setFamilyName("Kten");
		user.setArea(1);
		user.setAuth(1);
		logger.info("SET User Information ---- ");
		logger.info(user.toString());
	}

	@Test
	public void JOIN_USER() {
		loginDao.deleteUserByUsername(null);
		// ~ Join User ( Insert data )
		customJdbcUserDetailManager.createUser();
		Integer seq = user.getSeq();
		assertThat(null, is(not(seq)));

		User getUser = loginDao.selectUser(seq);

		logger.info("Get User Information ---- Seq : " + seq);
		logger.info(getUser.toString());

		assertUserInfo(user, getUser);
	}

	@Test
	public void CHANGE_PASSWORD() {
		loginDao.deleteUserByUsername(null);
		customJdbcUserDetailManager.joinUser(user);

		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setNewPassword("cndjr5766");

		logger.info(user.toString());
		// ~ Change Password ( Update data )
		customJdbcUserDetailManager.changePassword(newUser);

		Integer seq = user.getSeq();
		User getUser = loginDao.selectUser(seq);
		logger.info("Get User Information ---- Seq : " + seq);
		logger.info("GET : " + getUser.toString());
		
		assertThat(getUser.getPassword(), is(newUser.getPassword()));
	}
	
	@Test
	public void CHANGE_AUTHORITY() {
		loginDao.deleteUserByUsername(null);
		customJdbcUserDetailManager.joinUser(user);

		Integer seq = user.getSeq();
		User newUser = new User();
		newUser.setSeq(seq);
		newUser.setAuth(Authority.MANAGER.getAuthority());
		newUser.setLastUpdateBy("Admin Id");

		logger.info(user.toString());
		// ~ Change Password ( Update authority )
		customJdbcUserDetailManager.updateUserByAdmin(newUser);

		User getUser = loginDao.selectUser(seq);
		logger.info("Get User Information ---- Seq : " + seq);
		logger.info(getUser.toString());
		assertThat(getUser.getAuth(), is(newUser.getAuth()));
	}

	@Test
	public void UPDATE_USER_BY_ADMIN() {
		loginDao.deleteUserByUsername(null);
		customJdbcUserDetailManager.joinUser(user);

		Integer seq = user.getSeq();

		User newUser = new User();
		newUser.setSeq(seq);
		newUser.setFirstName("Lee");
		newUser.setFamilyName("Martin");
		newUser.setLastUpdateBy("Admin ID");

		customJdbcUserDetailManager.updateUserByAdmin(newUser);

		User getUser = loginDao.selectUser(seq);
		logger.info("Get User Information ---- Seq : " + seq);
		logger.info(getUser.toString());
		
		assertThat(getUser.getFirstName(), is(newUser.getFirstName()));
		assertThat(getUser.getFamilyName(), is(newUser.getFamilyName()));
		assertThat(getUser.getLastUpdateBy(), is(newUser.getLastUpdateBy()));
	}

	@Test
	public void DELETE_USER_BY_ADMIN() {
		loginDao.deleteUserByUsername(null);
		for (int i = 0; i < 2; i++) {
			user.setUsername(i + "abc@abc.net");
			customJdbcUserDetailManager.joinUser(user);
		}

		int count = loginDao.selectUserCount(new UserFilter());
		assertThat(count, is(2));

		customJdbcUserDetailManager.deleteUserByAdmin(user.getSeq());

		count = loginDao.selectUserCount(new UserFilter());
		assertThat(count, is(1));
	}
/*
	@Test
	public void SELECT_USERS() throws Exception {
		loginDao.deleteUserByUsername(null);

		for (int i = 0; i < 13; i++) {
			user.setUsername(i + "abc@abc.net");
			customJdbcUserDetailManager.joinUser(user);
		}

		UserFilter userFilter = new UserFilter();
		int count = loginDao.selectUserCount(userFilter);
		assertThat(count, is(13));

		List<User> users = customJdbcUserDetailManager.selectUsers(userFilter);
		assertThat(users.size(), is(10));

		userFilter.setPage(2);
		users = customJdbcUserDetailManager.selectUsers(userFilter);
		assertThat(users.size(), is(3));
	}

	@Test
	public void ERROR_SELECT_USERS() {
		loginDao.deleteUserByUsername(null);

		for (int i = 0; i < 13; i++) {
			user.setUsername(i + "abc@abc.net");
			customJdbcUserDetailManager.joinUser(user);
		}

		UserFilter userFilter = new UserFilter();
		int count = loginDao.selectUserCount(userFilter);
		assertThat(count, is(13));

		List<User> users = new ArrayList<User>();
		try {
			userFilter.setSortDesc("ABC");
			users = customJdbcUserDetailManager.selectUsers(userFilter);
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(users.size(), is(0));
		}

		try {
			userFilter.setSortOrder("ABC");
			users = customJdbcUserDetailManager.selectUsers(userFilter);
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(users.size(), is(0));
		}
	}

	@Test
	public void ENABLE_OR_DISABLE_UPDATE_USER() {
		loginDao.deleteUserByUsername(null);

		customJdbcUserDetailManager.joinUser(user);

		Integer seq = user.getSeq();
		User newUser = new User();
		newUser.setSeq(seq);
		newUser.setEnabled(false);
		newUser.setLastUpdateBy("admin");

		customJdbcUserDetailManager.changeEnabled(newUser);

		User getUser = loginDao.selectUser(seq);
		logger.info("Get User Information ---- Seq : " + seq);
		logger.info(getUser.toString());
		assertThat(getUser.isEnabled(), is(newUser.isEnabled()));
	}

	@Test
	public void CHECK_EXIST_USERNAME() {
		loginDao.deleteUserByUsername(null);

		customJdbcUserDetailManager.joinUser(user);

		boolean exist = customJdbcUserDetailManager.checkUsername(user
				.getUsername());

		assertThat(exist, is(true));
	}

	@Test
	public void TEMPORARY_PASSWORD_SEND_EMAIL() {
		loginDao.deleteUserByUsername(null);
		customJdbcUserDetailManager.joinUser(this.user);

		String username = user.getUsername();
		User user = customJdbcUserDetailManager.sendTemporaryPassword(username);

		assertThat(this.user.getPassword(), is(not(user.getPassword())));
	}

	@Test
	public void FORGOT_EMAIL_CHECK_EMAIL_AND_FIRSTNAME() {
		loginDao.deleteUserByUsername(null);
		customJdbcUserDetailManager.joinUser(this.user);

		String username = user.getUsername();
		String firstName = user.getFirstName();

		// ~ Default
		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstName);

		boolean result = customJdbcUserDetailManager.checkUser(user);
		assertThat(result, is(true));
		// ~ Upper
		String upperFirstName = user.getFirstName().toUpperCase();
		user.setFirstName(upperFirstName);

		result = customJdbcUserDetailManager.checkUser(user);
		assertThat(result, is(true));
		// ~ Trim
		String trimFirstName = " " + user.getFirstName() + " ";
		user.setFirstName(trimFirstName);

		result = customJdbcUserDetailManager.checkUser(user);
	}
	*/
	private void assertUserInfo(User user, User getUser) {
		assertThat(getUser.getUsername(), is(user.getUsername()));
		assertThat(getUser.getPassword(), is(user.getPassword()));
		assertThat(getUser.getSalt(), is(user.getSalt()));
		assertThat(getUser.isEnabled(), is(user.isEnabled()));
		assertThat(getUser.getAuth(), is(user.getAuth()));
		assertThat(getUser.getFirstName(), is(user.getFirstName()));
		assertThat(getUser.getFamilyName(), is(user.getFamilyName()));
	}

}
