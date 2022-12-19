package com.github.arleyoliveira;

import com.github.arleyoliveira.domain.entity.Cliente;
import com.github.arleyoliveira.domain.repositorio.ClienteRepository;
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
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
        return args -> {
            System.out.println("Salvando clientes");
            clienteRepository.save(new Cliente("Arley"));
            clienteRepository.save(new Cliente("Guilherme"));
            clienteRepository.save(new Cliente("Warley"));


            System.out.println("Obtendo cliente pelo o nome Gui");
            boolean exist = clienteRepository.existsByNome("Guilherme");


            System.out.println("Existe um cliente com o nome Guilherme: " + exist);


            System.out.println("Obtendo cliente pelo o nome Arley");
            List<Cliente> clientes = clienteRepository.findByNomeContaining("ar");
            clientes.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
