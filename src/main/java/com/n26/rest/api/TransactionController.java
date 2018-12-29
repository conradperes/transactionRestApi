package com.n26.rest.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TransactionController {
	List<Transaction> transactions = new ArrayList<>();

	public TransactionController() {
	}

	@GET
	@Produces("application/json")
	@RequestMapping("/statistics")
	public List<Transaction> getStatisticTransactionLast60Seconds() {
		return new HelperTransaction().populateArrayList(transactions);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response addBook(Transaction transaction) {
		transactions.add(transaction);
		transactions.forEach(t -> System.out.println(t));
		return Response.created(URI.create("/" + transaction.getAmount())).build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<String> deleteAllTransactions() {
		// transactionService.deleteAll();
		return ResponseEntity.ok("Transactions deleted");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<Transaction> receiveData(@RequestBody Transaction transaction) {

		//ObjectMapper mapper = new ObjectMapper();
//		 Transaction transaction = null;
		try {
//			transactions.add(transaction);
//			transactions.forEach(t -> System.out.println(t));

			//transaction = mapper.readValue(transaction.toString(), Transaction.class);
			transactions.add(transaction);
			transactions.forEach(t -> System.out.println(t));
			//mapper.writer();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(transaction);
		}

		System.out.println(transaction);
		return ResponseEntity.created(URI.create("/" + transaction.getAmount())).build();
	}
}
