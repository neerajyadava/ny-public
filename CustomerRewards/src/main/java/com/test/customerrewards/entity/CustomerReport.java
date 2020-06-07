/**
 * 
 */
package com.test.customerrewards.entity;

import java.util.List;
import java.util.Map;

import com.test.customerrewards.entity.Customer.Transaction;

import lombok.Data;

/**
 * @author neerajyadav
 *
 */
@Data
public class CustomerReport {

	Map<String, List<Transaction>>  customers;
	private Long total;

}
