package com.github.arleyoliveira;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import com.github.arleyoliveira.domain.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class SalesApplication extends SpringBootServletInitializer {


    @GetMapping("/hello")
    public String helloWorld() {
        return "Sistema de vendas";
    }

    @Bean
    public CommandLineRunner init(
            @Autowired CustomerRepository customerRepository,
            @Autowired SaleRepository saleRepository
            ) {
        return args -> {
            System.out.println("Salvando clientes");
            Customer customer = new Customer("Arley");
            customerRepository.save(customer);

            /*Sale sale = new Sale();
            sale.setCustomer(customer);
            sale.setCreated(LocalDate.now());
            sale.setTotal(BigDecimal.valueOf(100.00));

            saleRepository.save(sale);


            customer = customerRepository.findCustomerFetchSales(customer.getId());

            System.out.println(customer);
            System.out.println(customer.getSales());

            saleRepository.findByCustomer(customer).forEach(System.out::println);*/
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
