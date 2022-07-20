package services;

import models.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomerService {

    private static CustomerService customerService = new CustomerService();

    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(String firstName, String lastName, String email){
        customerList.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        Customer customer = null;
        for(Customer c : customerList){
            if(c.getEmail() == customerEmail){
                customer = c;
            }
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers(){
        customerList.add(new Customer("Tiia", "Kansa", "tiia@gmail.com"));
        customerList.add(new Customer("Igor", "Kansa", "kittenLittle@gmail.com"));
        return customerList;
    }

    public static CustomerService getInstance(){
        return customerService;
    }

}
