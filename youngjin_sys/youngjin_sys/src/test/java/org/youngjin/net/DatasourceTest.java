package org.youngjin.net;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/org/youngjin/web/config/bean/root-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DatasourceTest {

	@Test
	public void test() {
		
		
		
		//fail("Not yet implemented");
	}

}
