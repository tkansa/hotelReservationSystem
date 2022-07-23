package services;

import models.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService customerService = new CustomerService();

    private List<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer("Tiia", "Kansa", "tiia@gmail.com"),
            new Customer("Igor", "Kansa", "kittenLittle@gmail.com")
    ));

    public void addCustomer(String firstName, String lastName, String email){
        customerList.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        Customer customer = null;
        for(Customer c : customerList){
            if(c.getEmail().equals(customerEmail)){
                customer = c;
            }
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers(){

        return customerList;
    }

    public static CustomerService getInstance(){
        return customerService;
    }

}
