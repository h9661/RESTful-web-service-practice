package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        //given
        Customer customer = new Customer(1L);
        customer.setFirstName("Jon");

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getCustomerUrl(), customerDTO.getCustomerUrl());
    }
}