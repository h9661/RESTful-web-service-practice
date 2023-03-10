package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/v1/customers")
    public ResponseEntity<CustomerListDTO> listCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK
        );
    }

    @GetMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") String Id){
        return new ResponseEntity<CustomerDTO>(
                customerService.findCustomerById(Long.valueOf(Id)).get(),
                HttpStatus.OK
        );
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/api/v1/customers")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomer(customerDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/api/v1/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        customerService.deleteCustomer(Long.valueOf(id));

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
