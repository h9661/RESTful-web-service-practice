package guru.springfamework.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String lastName;
    private String firstName;
    private String customerUrl;

    public Customer() {

    }

    public Customer(Long id) {
        Id = id;
        customerUrl = "/shop/customers/" + id;
    }
}
