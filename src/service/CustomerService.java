package service;
import model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static CustomerService customerService = null;

    private CustomerService() {
    }


    public static CustomerService getInstance() {
        if (null == customerService) {
            customerService = new CustomerService();
        }
        return customerService;
    }
    

    ArrayList<Customer> customers = new ArrayList<>();

    // Get Customer Email
    public Customer getCustomer(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {

                return customer;
            }
        }
        return null;
    }

    // Add new Customer
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        
        customers.add(customer);
    }


    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customers;
        }
}
