package com.n26.rest.api;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.n26.rest.api.helper.DateDifferentHelper;
public class Transaction implements Serializable {
	
	private static final String UTC = "UTC";
	private static final long serialVersionUID = -4524081563985383524L;
	private String amount;
	private String timestamp;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");

	public Transaction() {
		super();
	}
	public Transaction(String amount, Date timeStamp) throws ParseException {
		super();
		this.amount = amount;
		formatter.setTimeZone(TimeZone.getTimeZone(UTC));
		this.timestamp = formatter.format(timeStamp);
	}
	public Transaction(String amount, String timeStamp) throws ParseException {
		super();
		this.amount = amount;
		formatter.setTimeZone(TimeZone.getTimeZone(UTC));
		this.timestamp = formatter.format(timeStamp);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}



	public void setTimeStamp(Date timeStamp) throws ParseException {
		this.timestamp = formatter.format(timeStamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n" + "    \"amount\":");
		sb.append("\""+amount+"\"");
		sb.append(",\"timeStamp\": ");
		sb.append("\""+timestamp+"\"");
		sb.append("}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
    public boolean isAllowed(String date) {
		return DateDifferentHelper.differenceBeteweenDates(date);
    }

}
