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
            clienteRepository.salvar(new Cliente("Arley"));
            clienteRepository.salvar(new Cliente("Guilherme"));

            System.out.println("Obtendo todos os clientes");
            List<Cliente> clientes = clienteRepository.obterTodos();
            clientes.forEach(System.out::println);

            System.out.println("Atualizando todos os clientes");
            clientes.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " atualizado");
                clienteRepository.atualizar(cliente);
            });

            System.out.println("Obtendo cliente pelo o nome Gui");
            clientes = clienteRepository.obterPorNome("Gui");
            clientes.forEach(System.out::println);


            System.out.println("Deletando todos os clientes");
            clienteRepository.obterTodos().forEach(cliente -> {
                clienteRepository.deletar(cliente);
            });

            System.out.println("Obtendo todos os clientes");
            clientes = clienteRepository.obterTodos();

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                clientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
