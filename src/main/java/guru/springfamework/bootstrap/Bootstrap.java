package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer customer1 = new Customer(1L);
        customer1.setFirstName("hyun");
        customer1.setLastName("woo");

        Customer customer2 = new Customer(2L);
        customer2.setFirstName("jon");
        customer2.setLastName("Tom");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Data Loaded Category = " + categoryRepository.count());
        System.out.println("Data Loaded Customer = " + customerRepository.count());
    }
}
