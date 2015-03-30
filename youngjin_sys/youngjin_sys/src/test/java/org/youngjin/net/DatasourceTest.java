package org.youngjin.net;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.youngjin.net.invoice.InvoiceDao;
import org.youngjin.net.invoice.InvoiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/org/youngjin/net/config/bean/root-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)


public class DatasourceTest {
		
	@Test
	public void test() {
		InvoiceController ic = new InvoiceController();
		InvoiceService isv = new InvoiceService();
		InvoiceDao idao = new InvoiceDao();
		int result = ic.testJunit("ah@ggh@jmh@aef@afewf@afeaewf@1@2@3@", "@");
		int expect = 9;
		assertEquals(expect,result,0);
				
//		InvoiceGblFilter filter =  new InvoiceGblFilter();
//		filter.setArea("");
//		filter.setCarrier("");
//		filter.setCode("");
//		filter.setBranch("");
//		filter.setCarrier("");
//		filter.setStartPud("");
//		filter.setEndPud("");
//		filter.setProcess("inbound");
//		
		
//		
//		int result = ist.getInvoiceSettingGblListCount(filter);
//		int expect = 0;
//		
//		assertEquals(expect,result,0);
	}

}
