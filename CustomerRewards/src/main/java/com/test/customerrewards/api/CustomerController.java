/**
 * 
 */
package com.test.customerrewards.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.customerrewards.entity.Customer;
import com.test.customerrewards.entity.CustomerReport;
import com.test.customerrewards.service.CustomerService;

/**
 * @author neerajyadav
 *
 */
@RestController
@RequestMapping("/")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getRewards/{durationInMonths}")
	public CustomerReport getRewards(@PathVariable final Long durationInMonths) {
		return customerService.getRewards(durationInMonths);
	}

	@GetMapping("/getCustRewards/{id}/{durationInMonths}")
	public CustomerReport getCustRewards(@PathVariable final String id, @PathVariable final Long durationInMonths) {
		return customerService.getCustRewards(id, durationInMonths);
	}

	@PatchMapping("/addTransaction/{id}/{price}/{createdBy}")
	public Customer addTransaction(@PathVariable final String id,
			@PathVariable final Double price,
			@PathVariable(required=false) final String createdBy) {
		return customerService.addTransaction(id, price, Optional.ofNullable(createdBy)).orElse(null);
	}
}
