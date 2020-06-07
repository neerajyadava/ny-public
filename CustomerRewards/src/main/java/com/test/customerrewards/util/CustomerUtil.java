/**
 * 
 */
package com.test.customerrewards.util;

/**
 * @author neerajyadav
 *
 */
public class CustomerUtil {

	public static Long calculateRewards( final Long price) {
		if (price >50 && price <100) {
			return price - 50;		
		}else if(price >100){
			return ((price - 100) * 2+ 50);
		}
		return 0L;
	}	
}
