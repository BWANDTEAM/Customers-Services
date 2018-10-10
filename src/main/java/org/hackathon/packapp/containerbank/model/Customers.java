package org.hackathon.packapp.containerbank.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customers {

	 private List<Customer> advisors;

	    @XmlElement
	    public List<Customer> getCustomerList() {
	        if (advisors == null) {
	            advisors = new ArrayList<>();
	        }
	        return advisors;
	    }
}
