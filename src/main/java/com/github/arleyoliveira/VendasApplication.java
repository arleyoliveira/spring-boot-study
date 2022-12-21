package com.github.arleyoliveira;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {


    @GetMapping("/hello")
    public String helloWorld() {
        return "Sistema de vendas";
    }

    @Bean
    public CommandLineRunner init(@Autowired CustomerRepository clienteRepository) {
        return args -> {
            System.out.println("Salvando clientes");
            clienteRepository.save(new Customer("Arley"));
            clienteRepository.save(new Customer("Guilherme"));
            clienteRepository.save(new Customer("Warley"));


            System.out.println("Obtendo cliente pelo o nome Gui");
            boolean exist = clienteRepository.existsByName("Guilherme");


            System.out.println("Existe um cliente com o nome Guilherme: " + exist);


            System.out.println("Obtendo cliente pelo o nome Arley");
            List<Customer> clientes = clienteRepository.findBySimilarName("Ar");
            clientes.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
