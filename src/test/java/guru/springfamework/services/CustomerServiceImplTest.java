package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() throws Exception{
        List<Customer> customers = Arrays.asList(new Customer(1L), new Customer(2L));

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void findCustomerById() throws Exception{
        Customer customer = new Customer(1L);
        customer.setLastName("chan");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Optional<CustomerDTO> customerDTO = customerService.findCustomerById(1L);

        assertEquals(customer.getId(), customerDTO.get().getId());
        assertEquals(customer.getLastName(), customerDTO.get().getLastName());
    }

    @Test
    public void createNewCustomer() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO(1L);
        customerDTO.setFirstName("chan");
        customerDTO.setLastName("woo");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        assertEquals(savedDto.getId(), customer.getId());
        assertEquals(savedDto.getFirstName(), customer.getFirstName());
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer(1L);
        customer.setFirstName("chan");
        customer.setLastName("woo");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO updatingCustomer = new CustomerDTO(1L);
        updatingCustomer.setFirstName("chan");
        updatingCustomer.setLastName("woo");

        CustomerDTO returnedCustomer = customerService.updateCustomer(updatingCustomer);

        assertEquals(updatingCustomer.getFirstName(), returnedCustomer.getFirstName());
        assertEquals(updatingCustomer.getLastName(), returnedCustomer.getLastName());
    }

    @Test
    public void deleteCustomer() {
        //given
        Long idToDelete = 1L;

        //when
        customerService.deleteCustomer(idToDelete);

        //no 'when', since method has void return type

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}