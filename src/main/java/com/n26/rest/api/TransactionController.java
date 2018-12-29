package com.n26.rest.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.rest.api.helper.StatisticalHelper;

@RestController
public class TransactionController {
	List<Transaction> transactions = new ArrayList<>();

	public TransactionController() {
	}

	@GET
	@Produces("application/json")
	@RequestMapping("/statistics")
	public Statistic getStatisticTransactionLast60Seconds() {
		if(!transactions.isEmpty())
			return new StatisticalHelper().getSummary(transactions);
		else
			return StatisticalHelper.resetStatistics();
			
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<String> deleteAllTransactions() {
		transactions.clear();
		return ResponseEntity.ok("Transactions deleted");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<Transaction> receiveData(@RequestBody Transaction transaction) {

		// ObjectMapper mapper = new ObjectMapper();
//		 Transaction transaction = null;
		try {
//			transactions.add(transaction);
//			transactions.forEach(t -> System.out.println(t));

			// transaction = mapper.readValue(transaction.toString(), Transaction.class);
			if (transaction.isAllowed(transaction.getTimeStamp())) {
				transactions.add(transaction);
				transactions.forEach(t -> System.out.println(t));
			}else {
				return ResponseEntity.status(204).body(transaction);
			}
			// mapper.writer();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(transaction);
		}

		return ResponseEntity.created(URI.create("/" + transaction.getAmount())).build();
	}
}
