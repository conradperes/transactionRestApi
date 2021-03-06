package com.n26.rest.api;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.rest.api.helper.StatisticalHelper;
import com.n26.rest.api.helper.TransactionHelper;

@RestController
public class TransactionController {
	List<Transaction> transactions = new ArrayList<>();

	public TransactionController() throws ParseException {
		transactions.add(new Transaction("777.77", new Date()));
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
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json", value = "/all")
	public List<Transaction> getAllTransactions() {
		if(!transactions.isEmpty())
			return transactions;
		else
			return new ArrayList<>();
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<String> deleteAllTransactions() {
		if(TransactionHelper.deleteTransactions(transactions))
			return ResponseEntity.ok("Transactions deleted");
		else
			return ResponseEntity.status(204).body("Some error happend deleting transactions");
	}

//	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public ResponseEntity<Transaction> receiveData(@RequestBody Transaction transaction) {
//
//		// ObjectMapper mapper = new ObjectMapper();
////		 Transaction transaction = null;
//		try {
////			transactions.add(transaction);
////			transactions.forEach(t -> System.out.println(t));
//
//			// transaction = mapper.readValue(transaction.toString(), Transaction.class);
//			if (transaction.isAllowed(transaction.getTimeStamp())) {
//				transactions.add(transaction);
//				transactions.forEach(t -> System.out.println(t));
//			}else {
//				return ResponseEntity.status(204).body(transaction);
//			}
//			// mapper.writer();
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(transaction);
//		}
//
//		return ResponseEntity.created(URI.create("/" + transaction.getAmount())).build();
//	}
	
	@RequestMapping(value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	@POST
	public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
		try {
			if (transaction.isAllowed(transaction.getTimestamp())) {
				transactions.add(transaction);
				transactions.forEach(t -> System.out.println(t));
			} else {
				return ResponseEntity.status(204).body(transaction);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(transaction);
		}
		return ResponseEntity.created(URI.create("/" + transaction.getAmount())).build();
	}
}