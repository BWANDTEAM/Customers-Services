
package org.hackathon.packapp.containerbank.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.hackathon.packapp.containerbank.model.Customer;
import org.hackathon.packapp.containerbank.model.Customers;
import org.hackathon.packapp.containerbank.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Wavestone
 */
@Controller
public class CustomerController {

    private final BankService bankService;


    @Autowired
    public CustomerController(BankService bankService) {
        this.bankService = bankService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public @ResponseBody Customers showCustomerbyName(@RequestParam(value="name", required=false, defaultValue="")String name) throws DataAccessException, JsonProcessingException {
    	Customers result = new Customers();
    	result.getCustomerList().addAll(this.bankService.findCustomerByLastName(name));
  	  	return result;
    }
    
    /**
     * Custom handler for displaying an customer.
     *
     * @param customerId the ID of the customer to display
     * @return a ModelMap with the model attributes for the view
     * @throws JsonProcessingException 
     * @throws DataAccessException 
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Customer showCustomer(@PathVariable("customerId") int customerId) throws DataAccessException, JsonProcessingException {
    	return this.bankService.findCustomerById(customerId);
    }

    @RequestMapping(value = "/customers/{customerId}/edit", method = RequestMethod.POST)
    public @ResponseBody Customer processUpdateCustomerForm(@PathVariable("customerId") int customerId,@RequestBody Customer customer) {
    	customer.setId(customerId);
        this.bankService.saveCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers/new", method = RequestMethod.POST)
    public  @ResponseBody Customer processCreationForm(@RequestBody Customer customer) {
    	this.bankService.saveCustomer(customer);
        return customer;
    }
    
}
