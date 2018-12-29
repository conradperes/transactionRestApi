package com.n26.rest.api;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class TransactionController {
	
	
	public TransactionController() {
	}

	
	

	@GET
	@Produces("application/json")
	@RequestMapping("/statistics")
	public Transaction getStatisticTransactionLast60Seconds() {
		return new Transaction();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response addBook(Transaction transaction) {
		return Response.created(URI.create("/" + transaction.getAmount())).build();
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/transactions")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseEntity<String> deleteAllTransactions() {
		//transactionService.deleteAll();
		return ResponseEntity.ok("Transactions deleted");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/transactions")
	@Produces("application/json")
	@Consumes("application/json")
    public ResponseEntity<String> receiveData(Transaction transaction) {

        ObjectMapper mapper = new ObjectMapper();
        //Transaction transaction = null;

        try {
            transaction = mapper.readValue(transaction.toString(), Transaction.class);
            mapper.writer();
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Is not possible to read json file");
        }

        System.out.println(transaction);
        return ResponseEntity.ok("It works!");
    }
}
