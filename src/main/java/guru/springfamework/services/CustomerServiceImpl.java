package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> findCustomerById(Long Id) {
        Optional<Customer> customerOptional = customerRepository.findById(Id);

        if(customerOptional.isPresent())
            return Optional.of(customerMapper.customerToCustomerDTO(customerRepository.findById(Id).get()));

        // todo: handling not found exception.
        throw new ResourceNotFoundException("Resource Not Found.");
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO){
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setCustomerUrl("/api/v1/customer/" + customer.getId());

        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> returnedCustomer = customerRepository.findById(customerDTO.getId());

        if (!returnedCustomer.isPresent()) {
            // not present object.
        }

        Customer customer = returnedCustomer.get();

        customer.setLastName(customerDTO.getLastName());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setCustomerUrl(customerDTO.getCustomerUrl());

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long Id) {
        customerRepository.deleteById(Id);
    }
}
