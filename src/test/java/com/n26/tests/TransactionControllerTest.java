package com.n26.tests;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.n26.rest.api.Application;
import com.n26.rest.api.Transaction;
import com.n26.rest.api.helper.DateDifferentHelper;
import com.n26.rest.api.helper.TimeMilisecondHelper;
import com.n26.rest.api.helper.TransactionHelper;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {
	private static final String ACTUAL_DATE = TimeMilisecondHelper.fromMilisecondsToDate();
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void statisticalHelperTest() throws Exception {

    	this.mockMvc.perform(get("/statistics")).andDo(print()).andExpect(status().isOk() );
//      .andExpect(jsonPath("$.content").value("{\n" + 
//      		"    \"sum\": 0,\n" + 
//      		"    \"avg\": 0,\n" + 
//      		"    \"max\": 0,\n" + 
//      		"    \"min\": 0,\n" + 
//      		"    \"count\": 0\n" + 
//      		"}")
//    		  );
//		List<Transaction> transactions = populate();
//		Assert.assertNotNull(new StatisticalHelper().getSummary(transactions));
	}

	private List<Transaction> populate() throws ParseException {
		TransactionHelper thelper = new TransactionHelper();
		List<Transaction> transactions = thelper.populateArrayList();
		return transactions;
	}

	@Test
	public void populateTransactionArray() {
		try {
			List<Transaction> transactions = populate();
			Assert.assertNotNull("This list is populated", transactions);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	/**
	 * To this test can pass the programmer should alter the date to be the actual
	 * date including the last 60 seconds, otherwise the test would not pass It
	 * would return true if date is ok, otherwise false.
	 * 
	 * @throws Exception
	 */
	@Test
	public void differenceBetweenDates() throws Exception {
		Assert.assertTrue(DateDifferentHelper.differenceBeteweenDates(ACTUAL_DATE));
	}

	/**
	 * This method should get the current Miliseconds of the system (OS), then
	 * convert this to String in the current format yyyy-MM-dd'T'HH:mm:ss.SSSS'Z
	 */
	@Test
	public void getTheCurrentDateSystem() {
		Assert.assertNotNull("The actual date is returned in the expected format ",
				TimeMilisecondHelper.fromMilisecondsToDate());
	}

	@Test
	public void isTransactionAllowed() {

		Transaction t;
		try {
			t = new Transaction("7777", new Date());
			Assert.assertFalse("This transaction is not allowed", t.isAllowed(t.getTimestamp()));
		} catch (ParseException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deleteTransactions() {
		try {
			List<Transaction> transactions = populate();
			Assert.assertTrue(TransactionHelper.deleteTransactions(transactions));
		} catch (ParseException e) {
			Assert.fail();
			e.printStackTrace();
		}
		
	}
	
}
