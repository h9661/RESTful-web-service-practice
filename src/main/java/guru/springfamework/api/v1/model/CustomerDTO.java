package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long Id;
    private String lastName;
    private String firstName;
    private String customerUrl;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id) {
        Id = id;
        customerUrl = "/shop/customers/" + id;
    }
}
