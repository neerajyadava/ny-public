/**
 * 
 */
package com.test.customerrewards.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

/**
 * @author neerajyadav
 *
 */
@Data
public class Customer {
	private String customerId;
	private String customerName;

	private List<Transaction> transactions = new ArrayList<>();

	@Data
	public static class Transaction {
		private Double price;
		private Long rewards;
		private Long date;
		private String createdBy;
	}
}
