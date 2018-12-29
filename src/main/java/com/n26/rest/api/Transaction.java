package com.n26.rest.api;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.n26.rest.api.helper.DateDifferentHelper;
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = -4524081563985383524L;
	private String amount;
	private String timeStamp;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");

	public Transaction(String amount, Date timeStamp) throws ParseException {
		super();
		this.amount = amount;
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.timeStamp = formatter.format(timeStamp);
	}
	public Transaction(String amount, String timeStamp) throws ParseException {
		super();
		this.amount = amount;
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.timeStamp = formatter.format(timeStamp);
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}



	public void setTimeStamp(Date timeStamp) throws ParseException {
		this.timeStamp = formatter.format(timeStamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n" + "    \"amount\":");
		sb.append("\""+amount+"\"");
		sb.append(",\"timeStamp\": ");
		sb.append("\""+timeStamp+"\"");
		sb.append("}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}
	
    public boolean isAllowed(String date) {
		return new DateDifferentHelper().differenceBeteweenDates(date);
    }

}
