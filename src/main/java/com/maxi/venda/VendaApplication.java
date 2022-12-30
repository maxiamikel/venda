package com.maxi.venda;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.maxi.venda.entities.Pedido;
import com.maxi.venda.services.VendaService;

@SpringBootApplication
public class VendaApplication implements CommandLineRunner {
    @Autowired
    private VendaService service;

    public static void main(String[] args) {
        SpringApplication.run(VendaApplication.class, args).close();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        service.incluirDadosIniciais();
        Pedido pedido1 = service.recuperarPedidoPeloNúmero(1);
        System.out.println("Total do pedido [1]: " + pedido1.getValorTotal() + ", cliente: " + pedido1.getCliente().getNome());
    }
    
}
