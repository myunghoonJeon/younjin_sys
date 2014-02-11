package org.youngjin.net.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/org/youngjin/net/config/bean/root-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MailServiceImplTest {

	@Autowired
	private MailService mailService;

	@Autowired
	private MessageSource messageSource;

	@Test
	public void testSendMail() throws Exception {
		mailService.sendMail();
	}
}
