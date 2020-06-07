/**
 * 
 */
package com.test.customerrewards.service;

import static com.test.customerrewards.util.CustomerUtil.calculateRewards;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.customerrewards.entity.Customer;
import com.test.customerrewards.entity.Customer.Transaction;
import com.test.customerrewards.entity.CustomerReport;

/**
 * @author neerajyadav
 *
 */
@Service
public class CustomerService {

	@Autowired
	List<Customer> customers;

	public CustomerReport getRewards(final Long durationInMonths) {
		LocalDateTime localDateTime = LocalDateTime.now().minusMonths(durationInMonths);
		final Long durationNeededInMillis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		Map<String, List<Transaction>> result = new LinkedHashMap<>();
		final AtomicLong sum = new AtomicLong();
		customers.stream().filter(customer -> {
			// Period Check
			List<Transaction> transactions = customer.getTransactions().stream()
					.filter(transaction -> transaction.getDate() >= durationNeededInMillis)
					.collect(Collectors.toList());

			if (!transactions.isEmpty()) {
				result.put(customer.getCustomerId(), transactions);
				sum.getAndAdd(transactions.stream().map(t -> t.getRewards()).reduce((a, b) -> a + b).get());
				return true;
			} else {
				return false;
			}
		}).count();
		CustomerReport customerReport= new CustomerReport();
		customerReport.setCustomers(result);
		customerReport.setTotal(sum.get());		
		return customerReport;		
	}


	public CustomerReport getCustRewards(final String customerId, final Long durationInMonths) {
		Optional<Customer> optCustomer = customers.stream().filter(customer -> customerId.equalsIgnoreCase(customer.getCustomerId())).findFirst();
		CustomerReport customerReport = null;
		if (optCustomer.isPresent()) {
			LocalDateTime localDateTime = LocalDateTime.now().minusMonths(durationInMonths);
			final Long durationNeededInMillis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			Map<String, List<Transaction>> result = new LinkedHashMap<>();
			final AtomicLong sum = new AtomicLong();
			// Period Check
			List<Transaction> transactions = optCustomer.get().getTransactions().stream()
					.filter(transaction -> transaction.getDate() >= durationNeededInMillis)
					.collect(Collectors.toList());

			if (!transactions.isEmpty()) {
				result.put(optCustomer.get().getCustomerId(), transactions);
				sum.getAndAdd(transactions.stream().map(t -> t.getRewards()).reduce((a, b) -> a + b).get());
			} 
			customerReport= new CustomerReport();
			customerReport.setCustomers(result);
			customerReport.setTotal(sum.get());		
		}
		return customerReport;		
	}

	public Optional<Customer> addTransaction(final String customerId, final Double price,
			final Optional<String> createdBy) {
		Optional<Customer> optCustomer = customers.stream().filter(customer -> customerId.equalsIgnoreCase(customer.getCustomerId())).findFirst();
		if (optCustomer.isPresent()) {
			Transaction transaction = new Transaction();
			transaction.setPrice(price);
			transaction.setRewards(calculateRewards(price.longValue()));

			transaction.setDate(Calendar.getInstance().getTimeInMillis());
			createdBy.ifPresent(val -> transaction.setCreatedBy(val));
			optCustomer.get().getTransactions().add(transaction);
		}
		return optCustomer;
	}
}
