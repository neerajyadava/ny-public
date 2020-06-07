/**
 * 
 */
package com.test.customerrewards;

import static com.test.customerrewards.util.CustomerUtil.calculateRewards;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import com.test.customerrewards.entity.Customer;
import com.test.customerrewards.entity.Customer.Transaction;

/**
 * @author neerajyadav
 *
 */
@Configuration
public class CustomerConfiguration {

	@Bean("customers")
	@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
	public List<Customer> customers() {
		return createCustomers();
	}
	private List<Customer> createCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		Customer customer1 = new Customer();
		customer1.setCustomerId("C1");
		customer1.setCustomerName("Jack");
		customers.add(customer1);

		Transaction transaction = new Transaction();
		transaction.setPrice(142.00);
		transaction.setRewards(calculateRewards(transaction.getPrice().longValue()));
		transaction.setCreatedBy("Tom");
		LocalDateTime localDateTime = LocalDateTime.now().minusMonths(4);
		final Long durationNeededInMillis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		transaction.setDate(durationNeededInMillis);

		customer1.getTransactions().add(transaction);

		Customer customer2 = new Customer();
		customer2.setCustomerId("C2");
		customer2.setCustomerName("Harry");
		customers.add(customer2);

		Customer customer3 = new Customer();
		customer3.setCustomerId("C3");
		customer3.setCustomerName("Niel");
		customers.add(customer3);

		return customers;
	}

}
