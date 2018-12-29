package com.n26.rest.api.helper;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import com.n26.rest.api.Statistic;
import com.n26.rest.api.Transaction;

public class StatisticalHelper {

	public static void main(String[] args) {
		// new StatisticalHelper().getSummary();

	}

	public Statistic getSummary(List<Transaction> transactions) {
		SummaryStatistics stats = new SummaryStatistics();
		//		TransactionHelper thelper = new TransactionHelper();
		//		transactions = thelper.populateArrayList(transactions);
		transactions.forEach(t -> stats.addValue(Double.parseDouble(t.getAmount())));
		return getStatistic(stats, transactions);
	}

	private Statistic getStatistic(SummaryStatistics stats, List<Transaction> transactions) {
		Statistic s = new Statistic();
		s.setSum(stats.getSum());
		s.setAvg(getAverage(transactions));
		s.setMax(stats.getMax());
		s.setMin(stats.getMin());
		s.setCount(stats.getN());
		return s;
	}

	private String getJson(SummaryStatistics stats, List<Transaction> transactions) {
		StringBuilder json = new StringBuilder("{");
		json.append("\"sum\":" + "\"" + stats.getSum() + "\"");
		json.append(",\"avg\":" + "\"" + getAverage(transactions) + "\"");
		json.append(",\"max\":" + "\"" + stats.getMax() + "\"");
		json.append(",\"min\":" + "\"" + stats.getMin() + "\"");
		json.append(",\"count\":" + stats.getN());
		json.append("}");
		return json.toString();
	}

	private double getAverage(List<Transaction> transactions2) {
		double sum = 0; // average will have decimal point
		for (Transaction transaction : transactions2)
			sum += Double.valueOf(transaction.getAmount());
		double average = sum / transactions2.size();
		return average;
	}

}
