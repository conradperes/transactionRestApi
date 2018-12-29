package com.n26.rest.api.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import com.n26.rest.api.Transaction;

public class TransactionHelper {
	// SortedSet<Transaction> transactions = new TreeSet<Transaction>();

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss Z");
//
//		System.out.println(df.format(new Date()));
//		Transaction t = null;
//		try {
//			t = new Transaction("1", new Date());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		t.isAllowed();
		
		String timeValue = "2018-07-17T09:59:51.312";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
		LocalDateTime timeStart = LocalDateTime.parse(timeValue, formatter);
		LocalDateTime timeStop = timeStart.plusMinutes(1);
		if(timeStart.isBefore(timeStop)) {
			System.out.println("Time of start is Allowed!");
		}
		// new HelperTransaction().populateJava8();
//		try {
//			new TransactionHelper().populateArrayList();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

	}
	
	public static boolean deleteTransactions(List<Transaction> transactions) {
		transactions.clear();
		if(transactions.isEmpty())
			return true;
		return false;
	}

	private void populate() {
//		Transaction t = new Transaction();
//		t.setAmount("88283773");
//		t.setTimeStamp(new Date());
//		transactions.add(t);
//		t = new Transaction();
//		t.setAmount("777777");
//		t.setTimeStamp(new Date());
//		transactions.add(t);
//		transactions.forEach(ts -> System.out.println(ts));
	}

	private void populateJava8() throws ParseException {
		SortedSet<Transaction> transactionsByTimestamp = new TreeSet<>(Comparator.comparing(Transaction::getTimeStamp));

		transactionsByTimestamp.add(new Transaction("1", new Date()));
		transactionsByTimestamp.add(new Transaction("2", new Date()));
		transactionsByTimestamp.add(new Transaction("3", new Date()));
		transactionsByTimestamp.add(new Transaction("4", new Date()));
		transactionsByTimestamp.add(new Transaction("5", new Date()));

		System.out.println("Transactions by  Timestamp:");
		transactionsByTimestamp.forEach(System.out::println);

		SortedSet<Transaction> transactionsByAmout = new TreeSet<>(Comparator.comparing(Transaction::getAmount));
		transactionsByAmout.add(new Transaction("1", new Date()));
		transactionsByAmout.add(new Transaction("2", new Date()));
		transactionsByAmout.add(new Transaction("3", new Date()));
		transactionsByAmout.add(new Transaction("4", new Date()));
		transactionsByAmout.add(new Transaction("5", new Date()));
		System.out.println("Transactions by  Amount: ");
		transactionsByAmout.forEach(System.out::println);
	}

	private void populateConcurrentSkipListSet() throws ParseException {
		ConcurrentSkipListSet<Transaction> transactionsByTimestamp = new ConcurrentSkipListSet<>(
				Comparator.comparing(Transaction::getTimeStamp));

		transactionsByTimestamp.add(new Transaction("1", new Date()));
		transactionsByTimestamp.add(new Transaction("2", new Date()));
		transactionsByTimestamp.add(new Transaction("3", new Date()));
		transactionsByTimestamp.add(new Transaction("4", new Date()));
		transactionsByTimestamp.add(new Transaction("5", new Date()));

		System.out.println("Transactions by  Timestamp:");
		transactionsByTimestamp.forEach(System.out::println);

		ConcurrentSkipListSet<Transaction> transactionsByAmout = new ConcurrentSkipListSet<>(
				Comparator.comparing(Transaction::getAmount));
		transactionsByAmout.add(new Transaction("1", new Date()));
		transactionsByAmout.add(new Transaction("2", new Date()));
		transactionsByAmout.add(new Transaction("3", new Date()));
		transactionsByAmout.add(new Transaction("4", new Date()));
		transactionsByAmout.add(new Transaction("5", new Date()));
		System.out.println("Transactions by  Amount: ");
		transactionsByAmout.forEach(System.out::println);
	}

	public List<Transaction> populateArrayList() throws ParseException {
		List<Transaction> transactionsByTimestamp = new ArrayList<>();

		transactionsByTimestamp.add(new Transaction("1", new Date()));
		transactionsByTimestamp.add(new Transaction("2", new Date()));
		transactionsByTimestamp.add(new Transaction("3", new Date()));
		transactionsByTimestamp.add(new Transaction("4", new Date()));
		transactionsByTimestamp.add(new Transaction("5", new Date()));

		System.out.println("Transactions by  Timestamp:");
		sortByTimeStamp(transactionsByTimestamp);

		List<Transaction> transactionsByAmout = new ArrayList<>();
		transactionsByAmout.add(new Transaction("1", new Date()));
		transactionsByAmout.add(new Transaction("2", new Date()));
		transactionsByAmout.add(new Transaction("3", new Date()));
		transactionsByAmout.add(new Transaction("4", new Date()));
		transactionsByAmout.add(new Transaction("5", new Date()));
		System.out.println("Transactions by  Amount: ");
		sortByAmount(transactionsByAmout);
		return transactionsByTimestamp;
	}

	public List<Transaction> populateArrayList(List<Transaction> transactionsByTimestamp) throws ParseException {

		transactionsByTimestamp.add(new Transaction("1", new Date()));
		transactionsByTimestamp.add(new Transaction("2", new Date()));
		transactionsByTimestamp.add(new Transaction("3", new Date()));
		transactionsByTimestamp.add(new Transaction("4", new Date()));
		transactionsByTimestamp.add(new Transaction("5", new Date()));

		System.out.println("Transactions by  Timestamp:");
		sortByTimeStamp(transactionsByTimestamp);
		return transactionsByTimestamp;

	}

	public void sortByAmount(List<Transaction> transactionsByAmout) {
		transactionsByAmout = transactionsByAmout.stream().sorted(Comparator.comparing(Transaction::getAmount))
				.collect(Collectors.toList());
		transactionsByAmout.forEach(e -> System.out.println("AMOUNT:" + e.getAmount() + ", DATE:" + e.getTimeStamp()));
	}

	public void sortByTimeStamp(List<Transaction> transactionsByTimestamp) {
		transactionsByTimestamp = transactionsByTimestamp.stream()
				.sorted(Comparator.comparing(Transaction::getTimeStamp)).collect(Collectors.toList());
		transactionsByTimestamp
				.forEach(e -> System.out.println("AMOUNT:" + e.getAmount() + ", DATE:" + e.getTimeStamp()));
	}

}
