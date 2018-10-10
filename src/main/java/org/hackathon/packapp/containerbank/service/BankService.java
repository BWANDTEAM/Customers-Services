
package org.hackathon.packapp.containerbank.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.hackathon.packapp.containerbank.model.Customer;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Wavestone
 */
public interface BankService {

    Customer findCustomerById(int id) throws DataAccessException;

    void saveCustomer(Customer customer) throws DataAccessException;

    Collection<Customer> findCustomerByLastName(String lastName) throws DataAccessException;

}
