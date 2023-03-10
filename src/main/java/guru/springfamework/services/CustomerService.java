package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> findCustomerById(Long Id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long Id);
}
